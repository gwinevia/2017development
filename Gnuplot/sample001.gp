reset
set nokey                 # 凡例の非表示
set xrange [-10:10]       # x軸方向の範囲の設定
set yrange [-3:3]         # y軸方向の範囲の設定

set term gif animate      # 出力をgifアニメに設定
set output "sample1.gif"  # 出力ファイル名の設定

n0 = 0        # ループ変数の初期値
n1 = 2*pi     # ループ変数の最大値
dn = 2*pi/20  # ループ変数の増加間隔

load "sample001.plt" 