#! /bin/bash

#reset user and tickets files
cp ../printout/currentaccounts0.txt currentaccounts.txt;
cp ../printout/avialabletickets0.txt avialabletickets.txt;

#run 5 days
for i in (1..5); do
	./daily.sh $i;
done
