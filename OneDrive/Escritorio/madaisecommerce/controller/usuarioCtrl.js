const { v4: uuidv4 } = require('uuid');
const bcrypt = require('bcrypt');
const User = require("../models/usuarioModel");
const { getTokenData, getToken } = require("../config/jwt.config");
const { getTemplate, sendEmail } = require("../config/mail.config");
const asyncHandler = require("express-async-handler");
const validateMongoDbId = require("../utils/validateMongodbId");
const validateEmailFormat = require("../utils/validateMongodbEmail");
const config = require('../utils/config');
const jwt = require('jsonwebtoken');
const RolPermiso = require("../models/rolPermisoModel");
require('dotenv').config();

const createUser = async (req,res) => {
    try {

        //obtener la data del usuario: name, email
        const { name, email, password } = req.body;

        //verificar que el usuario no exista
        let user = await User.findOne({ email }) || null;

        if(user !== null){
            return res.json({
                succes: false,
                msg: 'Usuario ya existe'
            });
        }
        //generar el código 
        const code = uuidv4();

        //Encriptar la contraseña con bcrypt
        const hashedPassword = await bcrypt.hash(password, 10);
        
        //Crear un nuevo usuario
        user = new User ({ name, email, password: hashedPassword, code });

        //Generar un token
        const token = getToken({ email, code });

        //Obtener un template
        const template =  getTemplate(name, token);

        //Enviar email
        await sendEmail(email, 'VERIFICACION EMAIL DE REGISTRO MADAIS', template);
        await user.save();

        res.json({
            succes: true,
            msg: 'Registrado correctamente'
        });
    } catch (error) {
        console.log(error);
        return res.json({
            succes: false,
            msg: 'Erorr al registrar usuario'
    });
        
    }
};

const confirm =  async(req, res) =>{
    try {

        //obtener el token
        const { token } = req.params;

        //verificar la data del token
        const data = await getTokenData(token);

        if(data === null){
            return res.json({
                succes: false,
                msg: 'Error al obtener data'
            });
        }

        console.log(data);

        const { email, code } = data.data;
        //Buscar si existe el usuario
        const user = await User.findOne({ email }) || null;

        if(user === null){
            return res.json({
                succes: false,
                msg: 'Usuario no existe'
            });
        }

        //Verificar el codigo 

        if(code !== user.code){
            return res.redirect('/error.html');
        }
        //Actualizar usuario
        user.status = 'VERIFIED'
        await user.save();


        //Redireccionar a la confirmacion
        return res.redirect('/confirm.html');


        
    } catch (error) {
        return res.json({
            succes: false,
            msg: 'Erorr al confirmar usuario'
    });
    }
};

//Metodo EDITAR CLIENTE
const updateUser = asyncHandler(async (req, res) => {
    const { id } = req.params;
    validateMongoDbId(id);
    try {
      const updatedUser = await User.findByIdAndUpdate(id, req.body, {
        new: true,
      });
      res.json(updatedUser);
    } catch (error) {
      throw new Error(error);
    }
});

//Metodo ELIMINAR CLIENTE
const deleteUser = asyncHandler(async (req, res) => {
    const { id } = req.params;
    validateMongoDbId(id);
    try {
      const deletedUser = await User.findByIdAndDelete(id);
      res.json(deletedUser);
    } catch (error) {
      throw new Error(error);
    }
});

 //Metodo OBTENER USUARIO POR ID
const getUser = asyncHandler(async (req, res) => {
    const { id } = req.params;
    validateMongoDbId(id);
    try {
      const getUser = await User.findById(id);
      res.json(getUser);
    } catch (error) {
      throw new Error(error);
    }
});

//Metodo OBTENER TODOS USUARIOS
const getallUser = asyncHandler(async (req, res) => {
    try {
      const getallUser = await User.find();
      res.json(getallUser);
    } catch (error) {
      throw new Error(error);
    }
});

function validateToken(req, res, next){
  const accessToken = req.header['authorization'];
  if(!accessToken) res.send('Access Denied');

    jwt.verify(accessToken, config.secretKey, (err, user) => {
    
      if(err){
        res.send('Acces Denied, token Expired or incorrect');
      }else{
        next();
      }

    });
}
function generateAccessToken(user) {
  return jwt.sign(user, config.secretKey, { expiresIn: '5m' });
}

//Metodo AUTENTICAR USUARIO
const authenticateUser = async (req, res) => {
  const { email, password } = req.body;

  try {
    const validationResult = validateEmailFormat(email);
    if (validationResult !== undefined) {
      return res.status(400).json({
        msg: 'Formato de correo electrónico no válido',
        success: false,
      });
    }

    const user = await User.findOne({ email });

    if (user) {
      const isPasswordValid = await bcrypt.compare(password, user.password);

      if (isPasswordValid) {
        // Verifica si user.rol existe y tiene un ID antes de acceder a user.rol
        if (user.rol) {
          const rolPermisos = await RolPermiso.find({ rol_id: user.rol }).populate('permiso_id');
          const permisos = rolPermisos.map((rp) => rp.permiso_id.nombre);

          const tokenPayload = {
            userId: user._id,
            email: user.email,
            roleId: user.rol, // Cambiar "role" a "roleId" para representar el ID del rol
            permissions: permisos,
          };

          const signedToken = jwt.sign(tokenPayload, config.secretKey, { expiresIn: '5m' });

          // Establecer el token en el encabezado
          res.header('authorization', signedToken);

          res.json({
            msg: `Rol ID ${user.rol} ACTIVO en el sistema`, // Cambiar el mensaje para mostrar el ID del rol
            success: true,
            token: signedToken,
          });
        } else {
          // Manejar el caso  donde user.rol es null o undefined
          return res.status(404).json({
            msg: 'Rol no encontrado para el usuario o ID de rol no presente',
            success: false,
          });
        }
      } else {
        return res.status(401).json({
          msg: 'Correo Electrónico o Contraseña no registrados en el sistema ',
          success: false,
        });
      }
    } else {
      return res.status(404).json({
        msg: 'Usuario no registrado en el sistema',
        success: false,
      });
    }
  } catch (error) {
    return res.status(500).json({
      msg: 'Error al autenticar al usuario',
      success: false,
      error: error.message,
    });
  }
};


//Se exportan los metodos
module.exports = {
    createUser,
    updateUser,
    deleteUser,
    getUser,
    getallUser,
    authenticateUser,
};