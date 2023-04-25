#!/bin/sh

FILE=/check
if [ ! -f "$FILE" ]; then
    mongoimport --host database-service --port 27017 --db lab --collection hotels --type json --drop --file "/database/data/hotels.json" --jsonArray
    mongoimport --host database-service --port 27017 --db lab --collection flights --type json --drop --file "/database/data/flights.json" --jsonArray
    touch $FILE
fi



