import React, { useEffect, useState } from 'react';
import { getConsumerOrders } from '../api/order.api';
import OrderTable from '../components/order-table';

const OrderList = () => {
  
  const [orders, setOrders] = React.useState([]);

  React.useEffect(() => loadOrders(), []);

  function loadOrders() {
    getConsumerOrders()
      .then((response) => {
        setOrders(response.data);
        console.log(response.data)
      })
      .catch((err) => console.log(err));
  }

  return <OrderTable orders={orders}/>;


};
export default OrderList;

