const axios = require('axios');
const { handleMicroserviceError } = require('../errors/microservice.error.js');

const orderHost = process.env.ORDER_HOST;

exports.getOrders = async (req, res) => {
  try {
    const queryParams = req.query;
    // Make a request to the product microservice
    console.log( `Controller reached. Host:${orderHost}`)
    const ordersResponse = await axios.get(orderHost, { params: queryParams });

    // Extract the product data from the microservice response
    const orders = ordersResponse.data;

    res.json(orders);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.getOrderById = async (req, res) => {
  try {
    
    // Make a request to the product microservice
    console.log("Controller reached")
    const ordersResponse = await axios.get(`${orderHost}/${req.params.orderId}`);

    // Extract the product data from the microservice response
    const order = ordersResponse.data;

    res.json(order);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};

exports.createOrder = async (req, res) => {
  try {
    const orderData = req.body; // Assuming the order data is sent in the request body

    // Make a request to the product microservice to create the order
    const createdOrderResponse = await axios.post(orderHost, orderData);

    // Extract the created order data from the microservice response
    const createdOrder = createdOrderResponse.data;

    res.json(createdOrder);
  } catch (error) {
    handleMicroserviceError(error, res);
  }
};