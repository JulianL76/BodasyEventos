const express = require("express");
const {
  createTalla,
  updateTalla,
  deleteTalla,
  getTalla,
  getallTalla,
} = require("../controller/tallaCtrl");
//const { authMiddleware, isAdmin } = require("../middlewares/authMiddleware");
const router = express.Router();

router.post("/", createTalla);
router.put("/:id", updateTalla);
router.delete("/:id", deleteTalla);
router.get("/:id", getTalla);
router.get("/", getallTalla);

module.exports = router;
