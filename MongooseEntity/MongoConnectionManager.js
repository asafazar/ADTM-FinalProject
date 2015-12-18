var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/test');
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function (callback) {
  // yay!
});
var derivativesSchema = mongoose.Schema({
    ID: Number
});
var derivative = mongoose.model('derivatives', derivativesSchema);
var todayDerivative = new derivative({ ID: 25 });

//find all derivative
derivative.find(function (err, kittens) {
  if (err) return console.error(err);
  console.log(derivatives);
})

//find by filter
derivatives.find({ ID: /^25/ }, callback);

todayDerivative.save(function (err, fluffy) {
  if (err) return console.error(err);
  //some function to save
});