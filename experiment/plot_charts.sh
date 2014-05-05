mkdir -p ./plot/$1

./convert_date.sh $1

./plot_availability.sh $1 month
./plot_availability.sh $1 day

./plot_acc_cost.sh $1 month
./plot_acc_cost.sh $1 day

./plot_acc_down_time.sh $1 month
./plot_acc_down_time.sh $1 day

./plot_acc_pro.sh $1 month
./plot_acc_pro.sh $1 day

./plot_acc_rej_time.sh $1 month
./plot_acc_rej_time.sh $1 day

./plot_acc_rev.sh $1 month
./plot_acc_rev.sh $1 day

mv $1.month.dat ./plot/$1
mv $1.day.dat ./plot/$1
