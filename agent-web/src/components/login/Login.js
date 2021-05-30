import React, { useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { login } from "../../redux/actions/userActions";
import { useHistory } from "react-router-dom";
import { toast } from "react-toastify";
import LoginForm from "./LoginForm";

function Login({ login, ...props }) {
  const [loginForm, setLoginForm] = useState({
    ...props.loginForm,
  });
  const [errors, setErrors] = useState({});
  const [saving, setSaving] = useState(false);
  const history = useHistory();

  function handleChange(event) {
    const { name, value } = event.target;
    setLoginForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  }

  function formIsValid() {
    const { username, password } = loginForm;
    const errors = {};

    if (!username) errors.username = "Username is required.";
    if (!password) errors.password = "Password is required.";

    setErrors(errors);
    return Object.keys(errors).length === 0;
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (!formIsValid()) return;
    setSaving(true);

    const isAgent = login(loginForm);
    if (isAgent) {
      toast.success("Successfully logged in.");
      history.push("/");
    } else {
      setErrors({ onSubmit: "Bad credentials, try again!" });
      setSaving(false);
    }
  }

  return (
    <LoginForm
      onChange={handleChange}
      errors={errors}
      onSubmit={handleSubmit}
      saving={saving}
      loginForm={loginForm}
    />
  );
}

Login.propTypes = {
  loginForm: PropTypes.object.isRequired,
  login: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  const loginForm = {
    username: "",
    password: "",
  };
  return {
    loginForm,
  };
}

const mapDispatchToProps = {
  login,
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
