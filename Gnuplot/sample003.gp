set size ratio 0.7
set samples 256
set xrange [0:15]
set yrange [0:10]
set trange [0:5*pi]
set xtics 0, 2, 15
set ytics 0, 1, 10
set nokey
set parametric

set terminal gif animate optimize size 500, 500
set output "cycloid.gif"
set tics font 'Times,18'

load "sample003.plt"
n = 0