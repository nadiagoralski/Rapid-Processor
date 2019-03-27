#! /bin/bash

#reset user and tickets files
cp ../file/out/0users.db users.db;
cp ../file/out/0tickets.db tickets.db;

#run 5 days
for i in (1..5); do
	./daily.sh $i;
done
