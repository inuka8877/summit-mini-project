
import React from 'react';
import { Link } from 'react-router-dom'; 
import './NavBar.css'; 

const NavBar = () => {
  return (
    <div className="navbar">
      <div className="navbar-left">
        <h1>Sysco Store</h1>
      </div>
      <div className="navbar-right">
        <Link to="/orders" className="nav-link">
          Orders
        </Link>
        <Link to="/products" className="nav-link">
          Products
        </Link>
      </div>
    </div>
  );
};

export default NavBar;
