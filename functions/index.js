const functions = require("firebase-functions");

// // Create and deploy your first functions
// // https://firebase.google.com/docs/functions/get-started
//
 exports.helloWorld = functions.https.onRequest((request, response) => {
   functions.logger.info("Hello logs!");
   response.send("Hello from Firebase!");
 });
exports.getFlights = functions.https.onCall(async (data) => {

//    const originLocation = data.originLocation;
//    const departureDate = data.departureDate;
//    const returnDate = data.returnDate;

    const cheapestFlights = [
        {
            "CountryName": "France",
            "Price": "12",
            "ImageUrl": "https://content.skyscnr.com/c88da2b091534f7baf8536b3959ce83a/GettyImages-495057957.jpg?crop=400px:400px&quality=75"
        },
        {
            "CountryName": "Italy",
            "Price": "14",
            "imageUrl": "https://content.skyscnr.com/f348d79cfdf70286dc759d24618a23c3/GettyImages-182281845.jpg?crop=400px:400px&quality=75"
        },
        {
            "CountryName": "Spain",
            "Price": "15",
            "ImageUrl": "https://content.skyscnr.com/e0241c97c2b33b71e9c278bc89bb17ed/GettyImages-178640523.jpg?crop=400px:400px&quality=75"
        }
    ];

    return cheapestFlights;
});


 //Firebase function to get flights depending on the origin location, departure and return date.
