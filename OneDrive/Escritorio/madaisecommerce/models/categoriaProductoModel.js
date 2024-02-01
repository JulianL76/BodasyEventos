const mongoose = require('mongoose'); // Erase if already required

// Declare the Schema of the Mongo model
const categoriaproductoSchema = new mongoose.Schema({
    id_categoria:{
      type: String,
      required: true,
    },
    id_producto:{
        type: String,
        required: true,
        unique:true,
      },
  } ,{
      versionKey: false // para que no salga la __v la cual trata del control de versiones
    });
  

//Export the model
module.exports = mongoose.model("categoria_producto", categoriaproductoSchema);