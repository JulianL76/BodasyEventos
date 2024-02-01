// permisos.model.js
const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const permisoSchema = new Schema({
    nombre:{
        type: String,
        required: true,
        unique: true,
        index: true,
      },
    } ,{
        versionKey: false // para que no salga la __v la cual trata del control de versiones
      });

module.exports = mongoose.model('permisos', permisoSchema);