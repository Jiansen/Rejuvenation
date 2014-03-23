#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1


cat << __EOF | gnuplot
set term png
set output  "plot/$name/$name\_acc_rej_time.png"
set title "$name: accumulated rejuvenation time"

set xlabel 'Time'
set ylabel 'accumulated rejuvenation time'
set xrange [0: *]
set yrange [0: *]
plot "$name.dat"  using 1:7 with lines  title 'Accumulated Rejuvenation Time'

set output
__EOF
