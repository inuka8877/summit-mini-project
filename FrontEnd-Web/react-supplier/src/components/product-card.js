// ProductCard.js
import './product-card.css';
import React, { useState } from 'react';
import defaultImage from '../assets/default.png';

const defaultDescription = 'No Description Available';

const ProductCard = ({ imageUrl, price, productName, supplierId, productId, description }) => {

  // Helper function to check if the URL is valid
  const isValidImageUrl = (url) => {
    // A simple check for URL validity, adjust as needed
    try {
      new URL(url);
      return true;
    } catch (error) {
      return false;
    }
  };

  imageUrl = 'http://localhost:8000/uploads/1707809743751-619629596.png';
  // Use the default image if imageUrl is not provided
  const displayImageUrl = isValidImageUrl(imageUrl) ? imageUrl : defaultImage;

  return (
    <div className="product-card">
      <img src={displayImageUrl} alt={productName} className="product-image" />
      <div className="product-details">
        <h2 className="product-name">{productName}</h2>
        <p className="product-price">Price: ${price}</p>
        <p className="product-id">Product ID: {productId}</p>
        <p className="product-id">Supplier ID: {supplierId}</p>
        <p className="product-description">{description}</p>
      </div>
    </div>
  );
};

export default ProductCard;
