// import { registerApplication, start } from "single-spa";
// import {
//   constructApplications,
//   constructRoutes,
//   constructLayoutEngine,
// } from "single-spa-layout";
// import microfrontendLayout from "./microfrontend-layout.html";

// const routes = constructRoutes(microfrontendLayout);
// console.log(routes);
// const applications = constructApplications({
//   routes,
//   loadApp({ name }) {
//     return System.import(name);
//   },
// });
// const layoutEngine = constructLayoutEngine({ routes, applications });

// applications.forEach(registerApplication);
// layoutEngine.activate();
// start();
import React from "react";
import { createRoot} from "react-dom/client";
import { registerApplication, start } from "single-spa";
import {
  constructApplications,
  constructRoutes,
  constructLayoutEngine,
} from "single-spa-layout";
import StartingPage from "./pages/start-page";

const microfrontendLayout = `
  <single-spa-router>
    <main>
      <route path="/supplier">
        <application name="@Summit-Inuka/supplier"></application>
      </route>
      <route path="/consumer">
        <application name="@Summit-inuka/consumer-front-end"></application>
      </route>
      <route default>
        <div id="starting-page"></div>
      </route>
    </main>
  </single-spa-router>
`;

const routes = constructRoutes(microfrontendLayout);

const applications = constructApplications({
  routes,
  loadApp({ name }) {
    return System.import(name);
  },
});

const layoutEngine = constructLayoutEngine({ routes, applications });

applications.forEach(registerApplication);
layoutEngine.activate();


const start_page = document.getElementById("starting-page");

if (start_page) {
  const root = createRoot(start_page);
  root.render(<StartingPage />);
} 

start();


