const express = require('express');
const productController = require('../controllers/product.controller');

const router = express.Router();

router.get('/api/products', productController.getProducts);
router.get('/api/products/:productId',productController.getProductById);
router.patch('/api/products/:productId',productController.approveProduct);
router.delete('/api/products/:productId',productController.deleteProduct);
router.post('/api/products', productController.testData);
module.exports = router;
