set size ratio 0.2
set samples 256
set xrange [0:15]
set yrange [0:3]
set trange [0:5*pi]
set xtics 0, 2, 15
set ytics 0, 1, 3
set nokey
set parametric

set terminal gif animate optimize size 500, 500
set output "sample004.gif"
set tics font 'Times,18'

load "sample004.plt"
n = 0