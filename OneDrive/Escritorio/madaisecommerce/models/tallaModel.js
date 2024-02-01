const mongoose = require("mongoose"); 

// Declare the Schema of the Mongo model
var tallaSchema = new mongoose.Schema(
  {
    nombre: {
      type: String,
      required: true,
      unique: true,
      index: true,
    },
  },
  {
    timestamps: true,
  }
);

//Export the model
module.exports = mongoose.model("Talla", tallaSchema);
