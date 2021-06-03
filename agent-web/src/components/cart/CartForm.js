import React from "react";
import { Button } from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import TextInput from "../common/TextInput";
import "../common/Form.css";
import PropTypes from "prop-types";

function CartForm({ form, errors, saving, onSubmit, onChange }) {
  return (
    <>
      <h2>Your data</h2>
      {errors.onSubmit && <Alert severity="error">{errors.onSubmit}</Alert>}
      <TextInput
        name="customerName"
        label="Name"
        value={form.customerName}
        onChange={onChange}
        error={errors.customerName}
      />
      <TextInput
        name="customerSurname"
        label="Surname"
        value={form.customerSurname}
        onChange={onChange}
        error={errors.customerSurname}
      />
      <TextInput
        name="customerAddress"
        label="Address"
        value={form.customerAddress}
        onChange={onChange}
        error={errors.customerAddress}
      />

      <br />
      <br />
      <Button
        type="submit"
        disabled={saving}
        variant="outlined"
        color="primary"
        className="field"
        onClick={onSubmit}
      >
        {saving ? "Ordering..." : "Order"}
      </Button>
    </>
  );
}

CartForm.propTypes = {
  form: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  saving: PropTypes.bool.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
};

export default CartForm;
