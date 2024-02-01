const Categoria  = require("../models/categoriaModel");
const categoria_producto  = require("../models/categoriaProductoModel"); //pendiente
const asyncHandler = require("express-async-handler");
const validateMongoDbId = require("../utils/validateMongodbId");


// Convertir la primera letra del nombre a mayúscula y el resto a minúscula
const capitalizeFirstLetter = (word) => {
  return word.charAt(0).toUpperCase() + word.slice(1).toLowerCase();
};
//Metodo CREAR CATEGORIA
const createCategoria = async (req, res) => {
  try {
    let { nombre } = req.body;
    nombre = capitalizeFirstLetter(nombre);
    const newCategoria = await Categoria.create({ nombre });
    res.json(newCategoria);
  } catch (error) {
    // Sin el throw new Error(error);
    res.status(500).json({ message: 'Error al crear la categoría', error: error.message });
  }
};

//Metodo EDITAR CATEGORIA
const updateCategoria= asyncHandler(async (req, res) => {
    const { id } = req.params;
    validateMongoDbId(id);
    try {
      const updatedCategoria = await Categoria.findByIdAndUpdate(id, req.body, {
        new: true,
      });
      res.json(updatedCategoria);
    } catch (error) {
      throw new Error(error);
    }
});

//Metodo ELIMINAR CATEGORIA
const deleteCategoria = asyncHandler(async (req, res) => {
    const { id } = req.params;
    try {
      const productosEnCategoria = await categoria_producto.findOne({ id_categoria: id });
      if(productosEnCategoria == null){ 
        const deletedCategoria = await Categoria.findByIdAndDelete(id);
        res.json({ message: "La categoría fue eliminada con exito" });
        res.json(deletedCategoria);
      } else{
        res.json({ message: "La categoría contiene elementos" });
      }
    } catch (error) {
      throw new Error(error);
    }
});

//Metodo OBTENER CATEGORIA POR ID
const getCategoria = asyncHandler(async (req, res) => {
    const { id } = req.params;
    validateMongoDbId(id);
    try {  
      const getCategoria = await Categoria.findById(id);
      res.json(getCategoria);
    } catch (error) {
      throw new Error(error);
    }
});


//Metodo OBTENER CATEGORIAS
const getallCategoria = asyncHandler(async (req, res) => {
    try {
      const getallCategoria = await Categoria.find();
      res.json(getallCategoria);
    } catch (error) {
      throw new Error(error);
    }
});

//Se exportan los metodos
module.exports = {
    createCategoria,
    updateCategoria,
    deleteCategoria,
    getCategoria,
    getallCategoria,
};