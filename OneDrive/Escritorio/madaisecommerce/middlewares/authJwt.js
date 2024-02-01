const jwt = require('jsonwebtoken');
const config = require('../utils/config');
const User = require('../models/usuarioModel');
const Rol_Permiso = require('../models/rolPermisoModel');
const mongoose = require('mongoose');

const verifyToken = async (req, res, next) => {
  try {
    const token = req.headers['x-access-token'];

    if (!token) return res.status(400).json({ message: 'No token provided' });

    const decoded = jwt.verify(token, config.secretKey);
    const user = await User.findById(decoded.userId, { password: 0 });

    if (!user) return res.status(400).json({ message: 'No user found' });

    req.userId = decoded.userId;
    req.user = user;
    next();
  } catch (error) {
    return res.status(401).json({ message: 'Unauthorized' });
  }
};

const checkPermission = (requiredPermission) => {
  return async (req, res, next) => {
    try {
      const roleId = new mongoose.Types.ObjectId(req.user.rol);

      
      console.log('Antes de la consulta a la base de datos');
      console.log('id_Usuario:', roleId);


      const rolPermisos = await Rol_Permiso.find({ idRol: roleId }).populate('idPermiso');

      console.log('Después de la consulta a la base de datos');
      console.log('Roles y Permisos encontrados:', rolPermisos);


      const userPermissions = rolPermisos.map((rp) => (rp.idPermiso));

      console.log('Permisos del usuario:', userPermissions);
      console.log('Permiso requerido:', requiredPermission);

      if (!userPermissions || !userPermissions.includes(requiredPermission)) {
        return res.status(403).json({
          msg: 'No tiene permisos para acceder a esta ruta.',
          success: false,
        });
      }

      next();
    } catch (error) {
      return res.status(500).json({
        msg: 'Error al verificar los permisos del usuario',
        success: false,
        error: error.message,
      });
    }
  };
};



module.exports = {
  verifyToken,
  checkPermission,
};
