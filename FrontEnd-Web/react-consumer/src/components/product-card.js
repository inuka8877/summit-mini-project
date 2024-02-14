// ProductCard.js
import './product-card.css';
import React, { useState } from 'react';
import defaultImage from '../assets/default.png';

const defaultDescription = 'No Description Available';

const ProductCard = ({ imageUrl, price, productName, supplierId, productId, description }) => {
  const [isOrderModalOpen, setOrderModalOpen] = useState(false);
  const [quantity, setQuantity] = useState(1);

  const handleOrderClick = () => {
    setOrderModalOpen(true);
  };

  const handleModalClose = () => {
    setOrderModalOpen(false);
  };

  const handleQuantityChange = (event) => {
    setQuantity(parseInt(event.target.value, 10) || 1);
  };

  const handleOrderSubmit = () => {
    // Perform order submission logic, e.g., send data to the server
    console.log('Order Submitted:', {
      productName: name,
      productId,
      quantity,
    });

    // Close the modal after submission
    handleModalClose();
  };

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
        <button onClick={handleOrderClick} className="order-button">
          Order Now
        </button>
      </div>

      {isOrderModalOpen && (
        <div className="order-modal">
          <div className="modal-content">
            <span className="close-button" onClick={handleModalClose}>
              &times;
            </span>
            <h2>{name}</h2>
            <p>Product ID: {productId}</p>
            <label htmlFor="quantity">Quantity:</label>
            <input
              type="number"
              id="quantity"
              value={quantity}
              onChange={handleQuantityChange}
            />
            <label htmlFor="address">Delivery Address:</label>
            <input
              type="text"
              id="address"
              value={address}
              onChange={handleQuantityChange}
            />
            <button onClick={handleOrderSubmit}>Submit Order</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default ProductCard;
