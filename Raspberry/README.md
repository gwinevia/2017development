# Raspberry Pi 3 Model B 作業メモ

## (1)Raspbian Jessie SDディスク作成
以下の動作はすべてMacで実行．  
わりと時間がかかった…  

### Raspbian Jessieダウンロード
Raspbian Jessieの入ったSDを作成する．  
NOOBSは面倒だし，Raspbianしか使わないので今回はNOOBSを使わずに設定した．  
以下のサイトからダウンロードする．

* [Download Raspbian for Raspberry Pi](https://www.raspberrypi.org/downloads/raspbian/)

### ディスクのフォーマット
以下のサイトからダウンロードしたSD Formatterを使ってSDカードをフォーマットした．

* [SD Card Formatter - SD Association](https://www.sdcard.org/downloads/formatter_4/)

### Etcherを使用したイメージのSDへの書き込み
イメージのSDへの書き込みは，Etcherというソフトを使った．  
以下のサイトからダウンロードしてインストールする．

* [Etcher by Resin.io](https://etcher.io/)

## (2)Raspbian Jessie ソフトウェアセットアップ
最初に注意事項として，途中で再起動してしまうと日本語環境が完全にセットアップされず，文字化けしてしまう．  
一通りセットアップが完了してから再起動する(途中で再起動促されても再起動しない)．

### Raspi-configセットアップ
画面の左上にあるRaspberry Piマークのアイコンをクリックする．   
`Preference` > `Raspberry Pi Configuration`を選択する．  

* systemタブ開いて`Expand Filesystem`をクリックしてOKをクリック
* ホスト名変更
* Interfaceタブを開いて，SSHをEnableにする
* Localisationのタブを開いて`Set Local`や`Set Timezone`を自身の環境に合わせて設定
	- 日本語キーボードの場合は，`Japan` > `Japanese`に設定
	- WiFiの設定もJapanにしておく

### 日本語環境セットアップ
```
$ sudo apt-get update
$ sudo apt-get install ibus-mozc
```
ibusの設定を起動する．  
`設定` > `iBusの設定`を開き，入力メソッドを選択して追加．  
画面の右上(時刻などが表示されているところ)で日本語変換を選択できるようになる．

### ソフトウェアアップデート
```
$ sudo apt-get update
$ sudo apt-get upgrade
```

## (3)SSH接続
ターミナルでIPアドレスを確認する．
```
$ip addr
```
上記のコマンドで確認したIPアドレスで同じネットワーク内のPCからSSH接続できる．以下に例を示す．  
```
$ssh pi@192.168.11.11
```
初期ユーザー名はpi，初期パスワードはraspberry．  
公開鍵認証にしたり，HOSTNAME設定などをするとより簡単にログインできる．

## (4)3.5インチ ディスプレイ
保護ケースと3.5インチディスプレイとタッチパネル等がセットになっている製品をamazonで購入．  
> Kuman 3.5インチ ディスプレイ タッチパネル  
Raspberry Pi 3B 2B/B+/A+ /A/B/Zero に適用 320*480 解析度  
保護ケース　ヒート ￥3,200

保護ケースはちょっと壊れやすい…  
組み立て方も特に記載されていないので，安いから…という感じ．  
ケースの上蓋?を付けずにモニタを設置する設計．  
[3.5inch_RPi_LCD_(A)](http://www.waveshare.com/wiki/3.5inch_RPi_LCD_(A))に最新のドライバや設定・使用手順などが
記載されている．

## 参考URL
* [Raspberry Pi 3でRaspbian Jessieをセットアップする方法](http://karaage.hatenadiary.jp/entry/2016/04/03/080000)
* [Raspberry Pi 3を買ってMacを使ってWiFi接続とSSHの接続するまで](http://qiita.com/toshihirock/items/8e7f0887b565defe7989)
* [久しぶりにラズパイとLCDで遊んでます](http://sunao-mita.pgw.jp/blog/index.php/2016/08/17/lcd35i_for_rpi/)
* [3.5inch_RPi_LCD_(A)](http://www.waveshare.com/wiki/3.5inch_RPi_LCD_(A))
