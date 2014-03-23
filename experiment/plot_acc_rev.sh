#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_acc_rev.png"
set title "$name: accumulated revenue"

set xlabel 'Time'
set ylabel 'accumulated revenue'
set xrange [0: *]
plot "$name.dat"  using 1:4 with lines  title 'Accumulated Revenue'

set output
__EOF
