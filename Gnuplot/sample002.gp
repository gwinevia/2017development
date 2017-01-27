reset
set nokey                 # 凡例の非表示
set xrange [-50:50]       # x軸方向の範囲の設定
set yrange [-50:50]       # y軸方向の範囲の設定
set size square           # 図を正方形にする

set term gif animate      # 出力をgifアニメに設定
set output "sample2.gif"  # 出力ファイル名の設定

n0 = 1    # ループ変数の初期値
n1 = 99   # ループ変数の最大値
dn = 1    # ループ変数の増加間隔

load "sample002.plt" 