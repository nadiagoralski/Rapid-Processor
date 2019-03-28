#! /bin/bash

i = "$1";

if ["$#" -ne 1]; then
	echo "Please enter day number"; #Prompts to enter the day number
else
	for j in (1..3); do #run 3 sessions
		./ticket-seller < ../input/in$i"_"$j.txt >> /dev/null; #to save output space
	done #merges
	cat session*.txt > ../out/$imerge.txt; #saves the merged file
	rm session*;
	cat ../out/$imerge.txt > transactions.txt; #overwrites the transaction file
	java Main #runs the backend

	#saves the tickets and accounts files
	cp users.db ../out/$1users.db;
	cp tickets.db ../out/$1tickets.db;

	echo "Day: "$i" complete";

fi

