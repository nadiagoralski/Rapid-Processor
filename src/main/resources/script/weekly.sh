#! /bin/bash

#resets the user and tickets files
cp ../file/out/0users.db users.db;
cp ../file/out/0tickets.db tickets.db;

#runs for 5 days
for i in (1..5); do
	./daily.sh $i;
done
