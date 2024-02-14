// ProductForm.js
import React, { useState } from 'react';

const ProductForm = () => {
  const [productName, setProductName] = useState('');
  const [productDescription, setProductDescription] = useState('');
  const [productImage, setProductImage] = useState(null);

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    setProductImage(file);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Create a FormData object to append form data including the image
    const formData = new FormData();
    formData.append('productName', productName);
    formData.append('productDescription', productDescription);
    formData.append('productImage', productImage);

    // Send the form data to the backend for processing
    try {
      const response = await fetch('http://localhost:8000/api/products', {
        method: 'POST',
        body: formData,
      });

      // Handle the response from the backend
      if (response.ok) {
        // Product created successfully
        console.log('Product created successfully');
      } else {
        // Handle error from the backend
        console.error('Error creating product');
      }
    } catch (error) {
      console.error('Error connecting to the server', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Product Name:
        <input
          type="text"
          value={productName}
          onChange={(e) => setProductName(e.target.value)}
        />
      </label>

      <label>
        Product Description:
        <textarea
          value={productDescription}
          onChange={(e) => setProductDescription(e.target.value)}
        />
      </label>

      <label>
        Product Image:
        <input type="file" accept="image/*" onChange={handleImageChange} />
      </label>

      <button type="submit">Submit</button>
    </form>
  );
};

export default ProductForm;
