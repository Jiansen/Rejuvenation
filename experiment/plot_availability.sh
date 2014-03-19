#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_availability.png"
set title "$name: availability"

set xlabel 'Time'
set ylabel 'availability'
set xrange [0: *]
set yrange [0: 1.1]
plot "$name.dat"  using 1:2 with lines  title 'Availability'

set output
__EOF
