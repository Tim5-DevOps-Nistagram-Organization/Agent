import React from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@material-ui/core";
import PropTypes from "prop-types";

function ProductDelete({ open, product, saving, onClose, onDelete }) {
  return (
    <Dialog
      open={open}
      onClose={onClose}
      scroll={"body"}
      aria-labelledby="alert-dialog-title"
      aria-describedby="alert-dialog-description"
    >
      <DialogTitle id="alert-dialog-title">Delete</DialogTitle>
      <DialogContent>
        <DialogContentText>
          Are you sure you want to delete the {product.name}?
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={onDelete} disabled={saving} color="secondary">
          {saving ? "Deleting..." : "Delete"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

ProductDelete.propTypes = {
  open: PropTypes.bool.isRequired,
  product: PropTypes.object.isRequired,
  saving: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default ProductDelete;
