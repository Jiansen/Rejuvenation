#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1
scale=$2

cat << __EOF | gnuplot
set term png
set output  'plot/$name/accumulated_downtime_$scale.png'
set title "$name: accumulated downtime"

set xlabel "Time ($scale)"
set ylabel 'accumulated downtime'
set xrange [0: *]
set yrange [0: *]
plot "$name.$scale.dat"  using 1:6 with lines  title 'Accumulated Downtime'

set output
__EOF
