#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_accumulated downtime.png"
set title "$name: accumulated downtime"

set xlabel 'Time'
set ylabel 'accumulated downtime'
set xrange [0: *]
set yrange [0: *]
plot "$name.dat"  using 1:7 with lines  title 'Accumulated Downtime'

set output
__EOF
