const axios = require('axios');
const multer = require('multer');
const path = require('path');
const { handleMicroserviceError } = require('../errors/microservice.error.js');

const productHost = process.env.PRODUCT_HOST;


exports.getProducts = async (req, res) => {
  try {
    const queryParams = req.query;
    // Make a request to the product microservice
    console.log("Controller reached")
    const productsResponse = await axios.get(productHost, { params: queryParams });

    // Extract the product data from the microservice response
    const products = productsResponse.data;

    res.json(products);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.getProductById = async (req, res) => {
  try {
    
    // Make a request to the product microservice
    console.log("Controller reached")
    const productsResponse = await axios.get(`${productHost}/${req.params.productId}`);

    // Extract the product data from the microservice response
    const product = productsResponse.data;

    res.json(product);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.approveProduct = async (req, res) => {
  try {
    
    // Make a request to the product microservice
    console.log("Controller reached")
    const productsResponse = await axios.patch(`${productHost}/${req.params.productId}`);

    // Extract the product data from the microservice response
    const message = productsResponse.data;

    res.json(message);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.deleteProduct = async (req, res) => {
  try {
    
    // Make a request to the product microservice
    console.log("Controller reached")
    const productsResponse = await axios.delete(`${productHost}/${req.params.productId}`);

    // Extract the product data from the microservice response
    const message = productsResponse.data;

    res.json(message);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.testData = async (req, res) => {

  const storage = multer.diskStorage({
    destination: function (req, file, cb) {
      cb(null, 'uploads/'); // Define the destination folder for uploaded files
    },
    filename: function (req, file, cb) {
      cb(null, file.originalname); // Use the original filename
    },
  });
  
  const upload = multer({ storage: storage });

  upload.single('productImage')
  // Access uploaded file details using req.file

  // console.log(req)
  // Process other form data from req.body
  console.log('Product Name:', req.body.productName);
  console.log('Product Description:', req.body.productDescription);
  console.log('Product Image:', req.file);
  console.log(req.file ? `/uploads/${req.file.filename}`: null )

  // Add your logic to save the product data to a database or perform other actions

  // Respond to the client
  res.send('Product created successfully');
};






