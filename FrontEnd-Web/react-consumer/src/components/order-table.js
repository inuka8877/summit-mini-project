import React from 'react';
import './order-table.css';

const OrderTable = ({ orders }) => {
  return (
    <table>
      <thead>
        <tr>
          <th>Order ID</th>
          <th>Created Date</th>
          <th>Supplier ID</th>
          <th>Supplier Name</th>
          <th>Product ID</th>
          <th>Product Name</th>
          <th>Quantity</th>
          <th>Total Price</th>
          <th>Delivery Address</th>
        </tr>
      </thead>
      <tbody>
        {orders.map((order) => (
          <tr key={order.orderId}>
            <td>{order.orderId}</td>
            <td>{order.createdDate}</td>
            <td>{order.supplierId}</td>
            <td>{order.supplierName}</td>
            <td>{order.productId}</td>
            <td>{order.productName}</td>
            <td>{order.quantity}</td>
            <td>${order.totalPrice.toFixed(2)}</td>
            <td>{order.deliveryAddress}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default OrderTable;
