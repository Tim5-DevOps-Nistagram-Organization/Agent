import React from "react";
import { Route, Redirect } from "react-router-dom";
import { connect } from "react-redux";
import PropTypes from "prop-types";

const GuardedRoute = ({
  component: Component,
  isAgent,
  itIsAgent,
  redirect,
  ...rest
}) => {
  return (
    <Route
      {...rest}
      render={(props) =>
        itIsAgent === undefined || itIsAgent === isAgent ? (
          <Component {...props} />
        ) : (
          <Redirect to={redirect} />
        )
      }
    />
  );
};

GuardedRoute.propTypes = {
  isAgent: PropTypes.bool.isRequired,
  itIsAgent: PropTypes.bool,
  redirect: PropTypes.string.isRequired,
  component: PropTypes.object.isRequired,
};

function mapStateToProps(state) {
  return {
    isAgent: state.isAgent,
  };
}

export default connect(mapStateToProps, {})(GuardedRoute);
