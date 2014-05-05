# original unit 10 minutes
declare -i day_of_year
day_of_year=1+6*24*12*30

awk '!/#/ {$1= $1/(6 * 24 * 30);} 1' $1.dat > $1.month.dat
head -n $day_of_year $1.dat | awk '!/#/ {$1= $1/(6 * 24);} 1'  > $1.day.dat



