const functions = require("firebase-functions");

// // Create and deploy your first functions
// // https://firebase.google.com/docs/functions/get-started
//
 exports.helloWorld = functions.https.onRequest((request, response) => {
   functions.logger.info("Hello logs!");
   response.send("Hello from Firebase!");
 });

 //Firebase function to get flights depending on the origin location, departure and return date.
