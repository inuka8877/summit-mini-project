const express = require('express');
const orderController = require('../controllers/order.controller');

const router = express.Router();

router.get('/api/orders', orderController.getOrders);
router.get('/api/orders/:orderId',orderController.getOrderById);
router.post('/api/orders',orderController.createOrder);
// router.patch('/api/orders/:orderId',productController.approveProduct);
// router.delete('/api/orders/:orderId',productController.deleteProduct);

module.exports = router;
