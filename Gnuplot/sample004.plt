if (exist("n")==0 || n<0) n=0 #変数の初期化
title(n) = sprintf("t = %d",n)  #タイトル名
unset label
set label title(n)  font 'Times,20'  at 0 , 3.3 

theta = pi/20 * n
fx(t) = t<=theta ? t-sin(t) : 1/0
fy(t) = t<=theta ? 1-cos(t) : 1/0
plot fx(t),fy(t) w l,\
     cos(t)+theta,sin(t)+1 w l ,\
     fx(theta), fy(theta) with points pt 7 lc rgb "blue"

if (n<100)  n=n+1; reread