const mongoose = require("mongoose");

var productoSchema = new mongoose.Schema(
  {
    nombre: {
      type: String,
      required: true,
      trim: true,
    },
    slug: {
      type: String,
      required: true,
      unique: true,
      lowercase: true,
    },
    descripcion: {
      type: String,
      required: true,
    },
    precio: {
      type: Number,
      required: true,
    },
    categoria: {
      type: String,
      required: true,
    },
    variaciones: [
      {
        color: {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Color",
        },
        tallas: [{
            type: mongoose.Schema.Types.ObjectId,
            ref: "Talla",
        },],
        cantidades: [{
          type: Number,
          required: true,
        },],
        imagenes: [
          {
            public_id: String,
            url: String,
          },
        ],
      },  
  
    ],
  },
  { timestamps: true }
);
module.exports = mongoose.model("Producto", productoSchema);