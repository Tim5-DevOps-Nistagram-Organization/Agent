import React from "react";
import { Button } from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import TextInput from "../common/TextInput";
import "../common/Form.css";
import PropTypes from "prop-types";

function LoginForm({ loginForm, errors, saving, onSubmit, onChange }) {
  return (
    <form onSubmit={onSubmit}>
      <h2>Log in</h2>
      {errors.onSubmit && <Alert severity="error">{errors.onSubmit}</Alert>}
      <TextInput
        name="username"
        label="Username"
        value={loginForm.username}
        onChange={onChange}
        error={errors.username}
      />

      <TextInput
        name="password"
        label="Password"
        value={loginForm.password}
        onChange={onChange}
        error={errors.password}
        type="password"
      />

      <br />
      <br />
      <Button
        type="submit"
        disabled={saving}
        variant="outlined"
        color="primary"
        className="field"
      >
        {saving ? "Logging in..." : "Log in"}
      </Button>
    </form>
  );
}

LoginForm.propTypes = {
  loginForm: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  saving: PropTypes.bool.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default LoginForm;
