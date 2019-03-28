#!/usr/bin/env bash

i = "$1";

if ["$#" -ne 1]; then
    #Prompts to enter the day number
    echo "Please enter day number";
else
     #run 3 sessions
	for j in (1..3); do
	    ./app/pick-it-sales < ./app/AvailableUsers.txt ./app/CurrentUsers.txt ../in/$jinput.txt >> /dev/null;
	done
	#save merged file
	cat transactions*.txt > ../out/$imerge.txt;
	rm transactions*;
	#overwrite transaction file
	cat ../out/$imerge.txt > ../out/transactions.db;
	#run backend (Main)
	java Main

	#save tickets and accounts files
	cp ../file/users.db ../out/$iusers.db;
	cp ../file/tickets.db ../out/$itickets.db;

	echo "Day: "$i" complete";

fi

