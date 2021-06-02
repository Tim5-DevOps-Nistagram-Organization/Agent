import React from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@material-ui/core";
import PropTypes from "prop-types";
import TextInput from "../common/TextInput";

function ProductAddToCart({
  open,
  item,
  error,
  saving,
  onClose,
  onChange,
  onSubmit,
}) {
  return (
    <Dialog
      open={open}
      onClose={onClose}
      scroll={"body"}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title">
        Add to cart {item.product.name}
      </DialogTitle>
      <DialogContent>
        <TextInput
          name="quantity"
          label="Quantity"
          value={item.quantity}
          onChange={onChange}
          error={error}
          type="number"
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={onSubmit} disabled={saving} color="primary">
          {saving ? "Adding..." : "Add"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

ProductAddToCart.propTypes = {
  open: PropTypes.bool.isRequired,
  item: PropTypes.object.isRequired,
  error: PropTypes.string.isRequired,
  saving: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
};

export default ProductAddToCart;
