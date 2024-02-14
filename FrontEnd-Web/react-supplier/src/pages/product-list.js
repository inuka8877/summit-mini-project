import React, { useEffect, useState } from 'react';
import ProductCard from '../components/product-card'; 
import { getProducts } from '../api/product.api';

const ProductList = () => {
  
  const [products, setProducts] = React.useState([]);

  React.useEffect(() => loadProducts(), []);

  function loadProducts() {
    getProducts({supplierId:"4"})
      .then((response) => {
        setProducts(response.data);
        console.log(response.data)
      })
      .catch((err) => console.log(err));
  }

  return (
    <div className="product-list">
      {products.map((product) => (
        <ProductCard key={product.productId} {...product} />
      ))}
    </div>
  );
};

export default ProductList;
