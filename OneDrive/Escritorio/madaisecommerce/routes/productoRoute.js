const express = require("express");
const {
  createProducto,
  getaProducto,
  getAllProducto,
  updateProducto,
  deleteProducto,
} = require("../controller/productoCtrl");

//const { isAdmin, authMiddleware } = require("../middlewares/authMiddleware");
const router = express.Router();

router.post("/", createProducto);

router.get("/:id", getaProducto);

router.put("/:id", updateProducto);
router.delete("/:id", deleteProducto);

router.get("/", getAllProducto);

module.exports = router;


