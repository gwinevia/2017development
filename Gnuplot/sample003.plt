if (exist("n")==0 || n<0) n=0 #変数の初期化
title(n) = sprintf("t = %d",n)  #タイトル名
unset label 
set label title(n)  font 'Times,20'  at 0 , 10.3 

x1=1+n/10
x2=2+n/10
set object 2 rect from x1,5 to x2,6 fc lt 1

plot x1+0.5,5.5  with points pt 7 lc rgb "blue"

if (n<100)  n=n+1; reread