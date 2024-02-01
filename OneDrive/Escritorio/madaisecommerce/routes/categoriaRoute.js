const express = require("express");
const {
  createCategoria,
  updateCategoria,
  deleteCategoria,
  getCategoria,
  getallCategoria,
} = require("../controller/categoriaCtrl");
const router = express.Router();
const { authJwt } = require('../middlewares');
const { verifyToken, checkPermission } = authJwt;

router.post("/", [authJwt.verifyToken, authJwt.checkPermission('65b92d7c2d4d1eab56a08259')], createCategoria);
router.put("/:id", [authJwt.verifyToken, authJwt.checkPermission('65b93de42d4d1eab56a0825d')], updateCategoria);
router.delete("/:id",[authJwt.verifyToken, authJwt.checkPermission('65b92d6e2d4d1eab56a08258')], deleteCategoria);
router.get("/:id", getCategoria);

router.get("/", [authJwt.verifyToken, authJwt.checkPermission('65b92d542d4d1eab56a08257')], getallCategoria);

module.exports = router;