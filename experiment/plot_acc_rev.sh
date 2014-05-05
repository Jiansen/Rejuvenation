#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1
scale=$2

cat << __EOF | gnuplot
set term png
set output  "plot/$name/acc_rev_$scale.png"
set title "$name: accumulated revenue"

set xlabel "Time ($scale)"
set ylabel 'accumulated revenue'
set xrange [0: *]
plot "$name.$scale.dat"  using 1:4 with lines  title 'Accumulated Revenue'

set output
__EOF
