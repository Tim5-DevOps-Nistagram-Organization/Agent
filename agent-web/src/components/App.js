import "./App.css";
import { Switch } from "react-router-dom";
import Header from "./common/Header";
import GuardedRoute from "./GuardedRoute";
import Login from "./login/Login";
import PageNotFound from "./PageNotFound";
import React from "react";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function App() {
  return (
    <div className="App">
      <Header />
      <Switch>
        <GuardedRoute
          exact
          path="/"
          component={() => <h1>Home</h1>}
          redirect="/login"
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
