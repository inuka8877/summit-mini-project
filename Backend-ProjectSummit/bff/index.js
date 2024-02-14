const express = require('express');
const morgan = require("morgan");
const cors = require('cors');
const multer = require('multer');
const path = require('path');
require('dotenv').config();

const PORT = 8000;

const app = express();

app.use(cors());

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, 'uploads/'); // Define the destination folder for uploaded files
  },
  filename: function (req, file, cb) {
    const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1e9);
    cb(null, uniqueSuffix + path.extname(file.originalname)); // Add a unique suffix to the filename
  },
});

const upload = multer({ storage: storage });

app.use(express.json());
app.use(express.urlencoded({ extended: true }));
app.use(upload.single('productImage'));
app.use('/uploads', express.static('uploads'));

// Logging Requests in development stage
app.use(morgan('dev'));

const productRoutes = require('./routes/product.routes');
const orderRoutes = require('./routes/order.routes');
const authRoutes = require('./routes/auth.routes');

app.use(productRoutes);
app.use(orderRoutes);
app.use(authRoutes);

app.listen(PORT, () => {
  console.log(`BFF listening at http://localhost:${PORT}`);
});
