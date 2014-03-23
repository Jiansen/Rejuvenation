mkdir -p ./plot/$1

./plot_availability.sh $1
./plot_acc_cost.sh $1
./plot_acc_down_time.sh $1
./plot_acc_pro.sh $1
./plot_acc_rej_time.sh $1
./plot_acc_rev.sh $1
