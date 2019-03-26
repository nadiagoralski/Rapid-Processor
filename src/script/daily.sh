#! /bin/bash

i = "$1";

if ["$#" -ne 1]; then
	echo "Please enter day number";
else
	for j in (1..3); do #run 3 sessions
		./ticket-seller < ../input/in$i"_"$j.txt >> /dev/null; #to save output space
	done #merge
	cat session*.txt > ../printout/merge$i.txt; #save merged file
	rm session*;
	cat ../printout/merge$i.txt > transactions.txt; #overwrite transactoin file
	java Backend #run backend
	#save the tickets and accounts files
	cp currentaccounts.txt ../printout/currentaccounts$1.txt;
	cp avialabletickets.txt ../printout/avialabletickets$1.txt;

	echo "Day: "$i" complete";

fi

