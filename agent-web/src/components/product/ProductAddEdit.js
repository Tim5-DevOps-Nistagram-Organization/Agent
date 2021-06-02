import React from "react";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@material-ui/core";
import Alert from "@material-ui/lab/Alert";
import TextInput from "../common/TextInput";
import PropTypes from "prop-types";
import ImageInput from "../common/ImageInput";

function ProductAddEdit({
  open,
  form,
  errors,
  saving,
  imageUploaded,
  onClose,
  onSubmit,
  onChange,
  onUpload,
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
        {form.id ? "Edit" : "Add"}
      </DialogTitle>
      <DialogContent>
        {errors.onSubmit && <Alert severity="error">{errors.onSubmit}</Alert>}
        <TextInput
          name="name"
          label="Name"
          value={form.name}
          onChange={onChange}
          error={errors.name}
        />

        <TextInput
          name="price"
          label="Price"
          value={form.price}
          onChange={onChange}
          error={errors.price}
          type="number"
        />
        <TextInput
          name="onStock"
          label="On stock"
          value={form.onStock}
          onChange={onChange}
          error={errors.onStock}
          type="number"
        />
        <ImageInput
          label={"Image"}
          onChange={onChange}
          name={"imageId"}
          value={form.imageId}
          error={errors.imageId}
          imageUploaded={imageUploaded}
          onUpload={onUpload}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} color="primary">
          Cancel
        </Button>
        <Button onClick={onSubmit} disabled={saving} color="primary">
          {saving ? "Saving..." : "Save"}
        </Button>
      </DialogActions>
    </Dialog>
  );
}

ProductAddEdit.propTypes = {
  open: PropTypes.bool.isRequired,
  form: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
  saving: PropTypes.bool.isRequired,
  imageUploaded: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  onUpload: PropTypes.func.isRequired,
};

export default ProductAddEdit;
