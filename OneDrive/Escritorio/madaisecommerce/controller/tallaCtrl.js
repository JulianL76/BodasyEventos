const Talla = require("../models/tallaModel");
const asyncHandler = require("express-async-handler");
const validateMongoDbId = require("../utils/validateMongodbId");

const createTalla = asyncHandler(async (req, res) => {
  try {
    const nuevaTalla = await Talla.create(req.body);
    res.json(nuevaTalla);
  } catch (error) {
    throw new Error(error);
  }
});
const updateTalla = asyncHandler(async (req, res) => {
  const { id } = req.params;
  validateMongoDbId(id);
  try {
    const tallaExistente = await Talla.findByIdAndUpdate(id, req.body, {
      new: true,
    });
    res.json(tallaExistente);
  } catch (error) {
    throw new Error(error);
  }
});
const deleteTalla = asyncHandler(async (req, res) => {
  const { id } = req.params;
  validateMongoDbId(id);
  try {
    const tallaEliminada = await Talla.findByIdAndDelete(id);
    res.json(tallaEliminada);
  } catch (error) {
    throw new Error(error);
  }
});
const getTalla = asyncHandler(async (req, res) => {
  const { id } = req.params;
  validateMongoDbId(id);
  try {
    const tallaObt = await Talla.findById(id);
    res.json(tallaObt);
  } catch (error) {
    throw new Error(error);
  }
});
const getallTalla = asyncHandler(async (req, res) => {
  try {
    const getallTalla = await Talla.find();
    res.json(getallTalla);
  } catch (error) {
    throw new Error(error);
  }
});
module.exports = {
  createTalla,
  updateTalla,
  deleteTalla,
  getTalla,
  getallTalla,
};
