const mongoose = require('mongoose'); // Erase if already required

// Declare the Schema of the Mongo model
const categoriaSchema = new mongoose.Schema({
    nombre:{
      type: String,
      required: true,
      unique: true,
      index: true,
    },
  } ,{
      versionKey: false // para que no salga la __v la cual trata del control de versiones
    });
  

//Export the model
module.exports = mongoose.model("Categoria", categoriaSchema);