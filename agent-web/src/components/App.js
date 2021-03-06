import "./App.css";
import { Switch } from "react-router-dom";
import Header from "./common/Header";
import GuardedRoute from "./GuardedRoute";
import Login from "./login/Login";
import PageNotFound from "./PageNotFound";
import React from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import ProductManagement from "./product/ProductManagement";
import ReportManagement from "./report/ReportManagment";
import CartManagement from "./cart/CartManagement";

function App() {
  return (
    <div className="App">
      <Header />
      <Switch>
        <GuardedRoute
          exact
          path="/"
          component={ProductManagement}
          redirect="/login"
        />
        <GuardedRoute
          exact
          path="/cart"
          itIsAgent={false}
          component={CartManagement}
          redirect="/"
        />
        <GuardedRoute
          exact
          path="/reports"
          itIsAgent={true}
          component={ReportManagement}
          redirect="/"
        />
        <GuardedRoute
          exact
          path="/login"
          itIsAgent={false}
          component={Login}
          redirect="/"
        />
        <GuardedRoute component={PageNotFound} redirect="/" />
      </Switch>
      <ToastContainer autoClose={3000} hideProgressBar />
    </div>
  );
}

export default App;
