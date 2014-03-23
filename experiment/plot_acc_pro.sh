#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_acc_profit.png"
set title "$name: accumulated profit"

set xlabel 'Time'
set ylabel 'accumulated profit'
set xrange [0: *]
plot "$name.dat"  using 1:5 with lines  title 'Accumulated Profit'

set output
__EOF
