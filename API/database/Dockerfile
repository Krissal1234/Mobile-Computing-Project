FROM mongo



COPY flights.json /flights.json
COPY hotels.json /hotels.json
CMD mongoimport --host database-service --db spot --collection hotels --type json --file /hotels.json --jsonArray && mongoimport --host database-service --port 27017 --db spot --collection flights --type json --file /flights.json --jsonArray
