import NavBar from "./components/navbar";
import ProductList from "./pages/product-list";
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import OrderList from "./pages/order-list";
import LoginPage from "./components/login-page";
import { useState, useEffect } from "react";
import ProductForm from "./components/product-form";

export default function Root(props) {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  // Check authentication status on component mount
  useEffect(() => {
    // Add your authentication check logic here
    // For simplicity, let's assume the user is not logged in initially
    setIsLoggedIn(false);
  }, []);

  return (
    <Router basename="/supplier">
      <ProductForm/>
      <div>
        <NavBar />
        <Routes>
          {isLoggedIn ? (
            <>
              <Route path="/" element={<Navigate to="/products" />} />
              <Route path="/products" element={<ProductList />} />
              <Route path="/orders" element={<OrderList />} />
            </>
          ) : (
            <>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<LoginPage setIsLoggedIn={setIsLoggedIn} />} />
            </>
          )}
        </Routes>
      </div>
    </Router>
  );
};