const express = require("express");
const {
  createUser,
  updateUser,
  deleteUser,
  getUser,
  authenticateUser,
  getallUser,
} = require("../controller/usuarioCtrl");
const router = express.Router();

const { authJwt } = require('../middlewares');

router.post("/",[authJwt.verifyToken, authJwt.checkPermission], createUser);
router.put("/:id", updateUser);
router.delete("/:id",[authJwt.verifyToken, authJwt.checkPermission('deleteCategoria')], deleteUser);
router.get("/:id",[authJwt.verifyToken, authJwt.checkPermission], getUser);
router.post("/authenticate", authenticateUser);
router.get("/", [authJwt.verifyToken, authJwt.checkPermission('verCategorias')], getallUser);

module.exports = router;