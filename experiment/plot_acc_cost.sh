#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_accumulated_cost.png"
set title "$name: accumulated cost"

set xlabel 'Time'
set ylabel 'accumulated cost'
set xrange [0: *]
set yrange [0: *]
plot "$name.dat"  using 1:3 with lines  title 'Accumulated Cost'

set output
__EOF
