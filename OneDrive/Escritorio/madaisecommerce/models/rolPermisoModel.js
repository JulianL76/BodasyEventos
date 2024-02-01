// roles_permisos.model.js
const mongoose = require('mongoose');

const rolPermisoSchema = new mongoose.Schema({
  idRol: {
    type: String,
    required: true,
  },
  idPermiso: {
    type: String,
    required: true,
    unique:true,
  },
}, {
  versionKey: false,
}, 

{
  timestamps: true,
}
);

module.exports = mongoose.model("rol_permiso", rolPermisoSchema);