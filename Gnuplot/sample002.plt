if(exist("n")==0 || n<0) n = n0  # ループ変数の初期化

plot "sample002.dat"  index n using 1:2 with lines # n番目のデータのプロット

n = n + dn            # ループ変数の増加
if ( n < n1 ) reread  # ループの評価
undefine n            # ループ変数の削除