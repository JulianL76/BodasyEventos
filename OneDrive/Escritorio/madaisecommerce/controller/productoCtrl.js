const Product = require("../models/productoModel");
const asyncHandler = require("express-async-handler");
const slugify = require("slugify"); 


const createProducto = asyncHandler(async (req, res) => {
  try {
    if (req.body.nombre) {
      req.body.slug = slugify(req.body.nombre);
    }
    const newProduct = await Product.create(req.body);
    res.json(newProduct);
  } catch (error) {
    throw new Error(error);
  }
});

const getaProducto = asyncHandler(async (req, res) => {
  const { id } = req.params;
  try {
    const findProduct = await Product.findById(id);
    res.json(findProduct);
  }
  catch (error) {
    throw new Error(error);
  }
});


const getAllProducto = asyncHandler(async (req, res) => {
  try {
    // Filtering
    const queryObj = { ...req.query };
    const excludeFields = ["page", "sort", "limit", "fields"];
    excludeFields.forEach((el) => delete queryObj[el]);
    let queryStr = JSON.stringify(queryObj);
    queryStr = queryStr.replace(/\b(gte|gt|lte|lt)\b/g, (match) => `$${match}`);

    let query = Product.find(JSON.parse(queryStr));

    // Sorting

    if (req.query.sort) {
      const sortBy = req.query.sort.split(",").join(" ");
      query = query.sort(sortBy);
    } else {
      query = query.sort("-createdAt");
    }

    // limiting the fields

    if (req.query.fields) {
      const fields = req.query.fields.split(",").join(" ");
      query = query.select(fields);
    } else {
      query = query.select("-__v");
    }

    // pagination

    const page = req.query.page;
    const limit = req.query.limit;
    const skip = (page - 1) * limit;
    query = query.skip(skip).limit(limit);
    if (req.query.page) {
      const productCount = await Product.countDocuments();
      if (skip >= productCount) throw new Error("This Page does not exists");
    }
    const product = await query;
    res.json(product);
  } catch (error) {
    throw new Error(error);
  }
});

const updateProducto = asyncHandler(async (req, res) => {
  const { id } = req.params;
  try {
    if (req.body.nombre) {
      req.body.slug = slugify(req.body.nombre);
    }

    const updateProduct = await Product.findOneAndUpdate(
      { _id: id }, // Utiliza _id en lugar de solo id
      req.body,
      { new: true }
    );

    if (!updateProduct) {
      return res.status(404).json({ error: "Producto no encontrado" });
    }

    res.json(updateProduct);
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Error interno del servidor" });
  }
});

const deleteProducto = asyncHandler(async (req, res) => {
  const { id } = req.params;
  try {
    const deleteProduct = await Product.findOneAndDelete({_id: id});
    res.json(deleteProduct);
  } catch (error) {
    throw new Error(error);
  }
});


module.exports = { createProducto, getaProducto, getAllProducto, updateProducto, deleteProducto};


