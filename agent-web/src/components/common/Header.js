import React, { useEffect } from "react";
import { NavLink, useHistory } from "react-router-dom";
import { Button } from "@material-ui/core";
import Avatar from "@material-ui/core/Avatar";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { toast } from "react-toastify";
import { makeStyles } from "@material-ui/core/styles";
import { blue } from "@material-ui/core/colors";
import { logout, checkUserIsAgent } from "../../redux/actions/userActions";
import { cartCheck } from "../../redux/actions/cartActions";

const useStyles = makeStyles((theme) => ({
  ava: {
    width: theme.spacing(3),
    height: theme.spacing(3),
    color: theme.palette.getContrastText(blue[300]),
    backgroundColor: blue[300],
  },
}));

function Header({ isAgent, cartSize, logout, checkUserIsAgent, cartCheck }) {
  const activeStyle = { color: "#fc9d7f" };

  const history = useHistory();
  const classes = useStyles();

  useEffect(() => {
    checkUserIsAgent();
    cartCheck();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  function handleLogOut() {
    logout();
    toast.success("Successfully logged out.");
    history.push("/login");
  }

  return isAgent ? (
    <nav>
      <Button>
        <NavLink to="/" activeStyle={activeStyle} exact>
          Product
        </NavLink>
      </Button>
      {" | "}
      <Button>
        <NavLink to="/reports" activeStyle={activeStyle}>
          Reports
        </NavLink>
      </Button>
      {" | "}
      <Button onClick={handleLogOut}>Log out</Button>
    </nav>
  ) : (
    <nav>
      <Button>
        <NavLink to="/" activeStyle={activeStyle} exact>
          Products
        </NavLink>
      </Button>
      {" | "}
      <Button>
        <NavLink to="/cart" activeStyle={activeStyle}>
          Cart
        </NavLink>
        <Avatar className={classes.ava}>{cartSize}</Avatar>
      </Button>
      {" | "}
      <Button>
        <NavLink to="/login" activeStyle={activeStyle}>
          Log In
        </NavLink>
      </Button>
    </nav>
  );
}

Header.propTypes = {
  isAgent: PropTypes.bool.isRequired,
  cartSize: PropTypes.number.isRequired,
  logout: PropTypes.func.isRequired,
  checkUserIsAgent: PropTypes.func.isRequired,
  cartCheck: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  return {
    isAgent: state.isAgent,
    cartSize: state.cart.length,
  };
}

const mapDispatchToProps = {
  logout,
  checkUserIsAgent,
  cartCheck,
};

export default connect(mapStateToProps, mapDispatchToProps)(Header);
