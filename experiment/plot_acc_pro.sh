#! /bin/sh 

# time | availability | accumulated cost | accumulated revenue | accumulated profit | accumulated downtime | accumulated rejuvenation time ")
name=$1
scale=$2

cat << __EOF | gnuplot
set term png
set output  "plot/$name/acc_profit_$scale.png"
set title "$name: accumulated profit"

set xlabel "Time ($scale)"
set ylabel 'accumulated profit'
set xrange [0: *]
plot "$name.$scale.dat"  using 1:5 with lines  title 'Accumulated Profit'

set output
__EOF
