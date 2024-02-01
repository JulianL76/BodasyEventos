const mongoose = require('mongoose');

var usuarioSchema = new mongoose.Schema({
  nombre: {
    type: String,
    required: true,
  },
  correo: {
    type: String,
    required: true,
    unique: true,
  },

  contrasenia: {
    type: String,
    required: true,
  },

  rol: {
    type: String,
    required: true, default: '65b8564f83f0fcf09e1c5fa4'
  },

  estado: {
    type: String,
    required: true, default: 'No Verificado'
  },

  code: {
    type: String,
    required: true,
  },
},
  {
    timestamps: true,
  }

);

module.exports = mongoose.model("Usuario", usuarioSchema);
