// StartingPage.js
import React from "react";
import "./start-page.css";
import { Link } from "react-router-dom";

function navigateTo(path) {
  window.location = path;
}

const StartingPage = () => (
  <div className="starting-page">
    <h1 className="greeting">Welcome to Sysco Store... 🚚</h1>
    <div>
      <div className="app-rectangle supplier-rectangle" onClick={() => navigateTo('/supplier')}>Go to Supplier Application</div>
      <div className="app-rectangle consumer-rectangle" onClick={() => navigateTo('/consumer')}>Go to Consumer Application</div>
    </div>
  </div>
);

export default StartingPage;
