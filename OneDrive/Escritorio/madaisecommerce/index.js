
const dotenv = require("dotenv").config();
const express = require("express"); 
const dbConnect = require("./config/dbConnect");
const app = express();
const authRouter=require("./routes/authRoute");
const productoRouter = require("./routes/productoRoute");
const tallaRouter = require("./routes/tallaRoute");
const colorRouter = require("./routes/colorRoute");
const userRouter=require("./routes/userRoute");
const categoriaRouter=require("./routes/categoriaRoute");
const bodyParser = require("body-parser");
const morgan =require("morgan");
const PORT = 4000;
dbConnect();
app.use(morgan('dev')); 
app.use(bodyParser.json());
app.use('/api/color', colorRouter)
app.use(bodyParser.urlencoded({ extended:false}));
app.listen(PORT, () => {
    console.log(`Server is running at PORT ${PORT}`);
});  
app.use('/api/user', authRouter)
app.use('/api/producto', productoRouter)
app.use('/api/talla', tallaRouter)
app.use('/api/categorias', categoriaRouter);
app.use(bodyParser.urlencoded({ extended:false}));

//app.listen(PORT, () => {
//console.log(`Server is running at PORT ${PORT}`);
//app.use('/api/users', userRouter)
//})