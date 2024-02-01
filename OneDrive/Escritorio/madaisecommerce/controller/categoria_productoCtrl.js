const categoria_producto  = require("../models/categoriaProductoModel");
const asyncHandler = require("express-async-handler");
const validateMongoDbId = require("../utils/validateMongodbId");

//Metodo CREAR CATEGORIA_PRODUCTO
const createCategoriaProducto = async (req,res) => {
    const producto = req.body.id_producto;
    const findProducto = await categoria_producto.findOne({id_producto:producto});
    if(!findProducto){
       const newProducto =await categoria_producto.create(req.body);
       res.json(newProducto);
    }
    else{
        res.json({
            msg: "El producto ya esta en una categoria",
            success: false,
        });
    }
};

//Metodo OBTENER CATEGORIAS
const getallCategoriaProducto = asyncHandler(async (req, res) => {
    try {
      const getallCategoria = await categoria_producto.find();
      res.json(getallCategoria);
    } catch (error) {
      throw new Error(error);
    }
});


/*

const getallCategoria = asyncHandler(async (req, res) => {
    try {
      const getallCategoria = await Categoria.find();
      res.json(getallCategoria);
    } catch (error) {
      throw new Error(error);
    }
});










*/
//Se exportan los metodos
module.exports = {
    createCategoriaProducto,
    getallCategoriaProducto,
};