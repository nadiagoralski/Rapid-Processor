#!/usr/bin/env bash

#resets the user and tickets files
cp ../out/0users.db ../file/users.db;
cp ../out/0tickets.db ../filetickets.db;

#runs for 5 days
for i in (1..5); do
	./daily.sh $i;
done
