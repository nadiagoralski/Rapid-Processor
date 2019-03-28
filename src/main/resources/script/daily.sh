#! /bin/bash

i = "$1";

if ["$#" -ne 1]; then
	echo "Please enter day number"; #Prompts to enter the day number
else
	for j in (1..3); do #run 3 sessions
		./app/pick-it-sales < ../in/$j""input.txt >> /dev/null; #to save output space
	done #merges
	cat transactions*.txt > ../out/$i""merge.txt; #saves the merged file
	rm transactions*;
	cat ../out/$i""merge.txt > transactions.db; #overwrites the transaction file
	java Main #runs the backend

	#saves the tickets and accounts files
	cp users.db ../out/$1users.db;
	cp tickets.db ../out/$1tickets.db;

	echo "Day: "$i" complete";

fi

