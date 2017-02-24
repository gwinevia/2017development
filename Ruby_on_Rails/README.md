# Ruby on Rails

* Rails 4.2.7.1

## rails + nginx + unicorn連携

### (1)Nginxインストール

```sh
# (1) nginxサイトが配布するPGPキーを追加
$ curl http://nginx.org/keys/nginx_signing.key | sudo apt-key add -

# (2) リポジトリを一覧に追加
$ sudo sh -c "echo 'deb http://nginx.org/packages/ubuntu/ wily nginx' >> /etc/apt/sources.list"
$ sudo sh -c "echo 'deb-src http://nginx.org/packages/ubuntu/ wily nginx' >> /etc/apt/sources.list"

# (3) アップデート後、nginxをインストール
$ sudo apt-get update
$ sudo apt-get install nginx
```

* (2)のリポジトリを追加するコマンド内にある`wily`はOSに応じて変更
* 参考：[Ubuntuに最新のnginxをインストールする - Qiita](http://qiita.com/hiroq/items/420424bc500d89fd1cc8)

### (2)Unicornの設定

* Gemfileにunicornを追加:```gem 'unicorn'```
* インストール:```$ bundle install```
* config/unicorn.rbを新規作成  

```ruby
# -*- coding: utf-8 -*-
worker_processes Integer(ENV["WEB_CONCURRENCY"] || 3)
timeout 15
preload_app true  # 更新時ダウンタイム無し

listen "/home/mmk/krswmmk/2017development/Ruby_on_Rails/work001/tmp/unicorn.sock"
pid "/tmp/unicorn.pid"

before_fork do |server, worker|
  Signal.trap 'TERM' do
    puts 'Unicorn master intercepting TERM and sending myself QUIT instead'
    Process.kill 'QUIT', Process.pid
  end

  defined?(ActiveRecord::Base) and
    ActiveRecord::Base.connection.disconnect!
end

after_fork do |server, worker|
  Signal.trap 'TERM' do
    puts 'Unicorn worker intercepting TERM and doing nothing. Wait for master to send QUIT'
  end

  defined?(ActiveRecord::Base) and
    ActiveRecord::Base.establish_connection
end

# ログの出力
stderr_path File.expand_path('log/unicorn.log', ENV['RAILS_ROOT'])
stdout_path File.expand_path('log/unicorn.log', ENV['RAILS_ROOT'])
```

* Rakeのtask に Unicorn の起動 & 終了の設定を記述:```$ bundle exec rails generate task unicorn```  

```ruby
namespace :unicorn do

  desc "Start unicorn for development env."
  task(:start) {
    config = Rails.root.join('config', 'unicorn.rb')
    sh "bundle exec unicorn_rails -c #{config} -E development -D"
  }

  desc "Stop unicorn"
  task(:stop) { unicorn_signal :QUIT }

  desc "Restart unicorn with USR2"
  task(:restart) { unicorn_signal :USR2 }

  desc "Increment number of worker processes"
  task(:increment) { unicorn_signal :TTIN }

  desc "Decrement number of worker processes"
  task(:decrement) { unicorn_signal :TTOU }

  desc "Unicorn pstree (depends on pstree command)"
  task(:pstree) do
    sh "pstree '#{unicorn_pid}'"
  end

  def unicorn_signal signal
    Process.kill signal, unicorn_pid
  end

  def unicorn_pid
    begin
      File.read("/tmp/unicorn.pid").to_i
    rescue Errno::ENOENT
      raise "Unicorn doesn't seem to be running"
    end
  end

end
```

* 下記のコマンドでUnicornを起動、停止出来る

```ruby
$ bundle exec rake unicorn:start
$ bundle exec rake unicorn:stop
```

* 参考：[Rails 4.2 + Unicorn + Nginx でアプリケーションサーバの構築 - Qiita](http://qiita.com/Salinger/items/5350b23f8b4e0dcdbe23)

### (3)Nginxの設定

* nginx用設定ファイル作成
	- /etc/nginx/conf.d/に`work001.conf`を作成(名前はなんでもいい)
	- /etc/nginx/conf.d/default.confは`_default.conf.bak`に変更して保存

```
upstream unicorn {
  server unix:/home/mmk/krswmmk/2017development/Ruby_on_Rails/work001/tmp/unicorn.sock;
}

server {
  listen 80 default_server;
  server_name サーバ名(IPアドレスやドメイン名など);

  access_log /var/log/nginx/sample_access.log;
  error_log /var/log/nginx/sample_error.log;

  root /home/mmk/krswmmk/2017development/Ruby_on_Rails/work001;

  client_max_body_size 100m;
  error_page 404 /404.html;
  error_page 500 502 503 504 /500.html;
  try_files $uri/index.html $uri @unicorn;

  location @unicorn {
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header Host $http_host;
    proxy_pass http://unicorn;
  }
}
```

* 起動/停止/再起動  

```sh
# 起動
$ sudo service nginx start

# 停止
$ sudo service nginx stop

# 再起動
$ sudo service nginx restart
```