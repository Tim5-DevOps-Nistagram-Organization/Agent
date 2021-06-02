import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import ProductList from "./ProductList";
import { newProduct } from "../../model/Product";
import ProductAddEdit from "./ProductAddEdit";
import { Button } from "@material-ui/core";
import ProductDelete from "./ProductDelete";
import ProductAddToCart from "./ProductAddToCart";
import { Item, newItem } from "../../model/Item";
import { cartAddItem } from "../../redux/actions/cartActions";
import * as productService from "../../services/ProductService";
import * as imageService from "../../services/ImageService";
import { toast } from "react-toastify";

function ProductManagement({ isAgent, cart, cartAddItem }) {
  const [open, setOpen] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);
  const [openAddToCart, setOpenAddToCart] = useState(false);
  const [product, setProduct] = useState(newProduct);
  const [item, setItem] = useState(newItem);
  const [errors, setErrors] = useState({});
  const [errorItem, setErrorItem] = useState("");
  const [saving, setSaving] = useState(false);
  const [image, setImage] = useState({});
  const [imageUploaded, setImageUploaded] = useState(true);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    getAll();
  }, []);

  function getAll() {
    productService.getAll().then((data) => {
      setProducts(data);
    });
  }

  function handleAddButton() {
    setProduct(newProduct);
    openAddEdit();
  }
  function handleEditButton(product) {
    setProduct(product);
    openAddEdit();
  }
  function openAddEdit() {
    setImageUploaded(true);
    setImage({});
    setOpen(true);
  }
  function handleDeleteButton(product) {
    setProduct(product);
    setOpenDelete(true);
  }

  function handleAddToCartButton(product) {
    setItem(new Item(product, 1));
    setOpenAddToCart(true);
  }
  
  function handleChange(event) {
    const { name, value, files } = event.target;
    if (name === "imageId") {
      setImage(files[0]);
      setImageUploaded(false);
      return;
    }
    setProduct((prevValue) => ({ ...prevValue, [name]: value }));
  }

  function formIsValid() {
    const { name, price, onStock } = product;
    const errors = {};

    if (price <= 0) errors.price = "Price must be grater then zero.";
    if (onStock < 0) errors.onStock = "On stock can not be negative number.";
    if (!name) errors.name = "Name is required.";
    if (!price) errors.price = "Price is required.";
    if (!onStock) errors.onStock = "On stock is required.";

    setErrors(errors);
    return Object.keys(errors).length === 0;
  }

  function handleSubmit() {
    setSaving(true);
    if (product.id != null) {
      handleEdit();
    } else {
      handleAdd();
    }
  }

  function handleAdd() {
    if (!formIsValid()) {
      setSaving(false);
      return;
    }
    productService
      .create(product)
      .then((data) => {
        setProducts((prevValue) => [...prevValue, data]);
        setSaving(false);
        setOpen(false);
      })
      .catch((err) => {
        setErrors({ onSubmit: JSON.parse(err.message).message });
        setSaving(false);
      });
  }

  function handleEdit() {
    if (!formIsValid()) {
      setSaving(false);
      return;
    }
    productService
      .update(product)
      .then((data) => {
        let newValue = [...products];
        newValue = newValue.map((p) => (p.id === data.id ? data : p));
        setProducts(newValue);
        setSaving(false);
        setOpen(false);
      })
      .catch((err) => {
        setErrors({ onSubmit: JSON.parse(err.message).message });
        setSaving(false);
      });
  }

  function handleDelete() {
    setSaving(true);
    productService
    .deleteById(product.id)
    .then((data) => {
      toast.success(data);
      let newValue = [...products];
      newValue = newValue.filter((p) => product.id !== p.id);
      setProducts(newValue);
      setSaving(false);
      setOpenDelete(false);
    })
    .catch((err) => {
      toast.error(JSON.parse(err.message).message);
      setSaving(false);
      setOpenDelete(false);
    });
}

  function handleChangeItem(event) {
    const { name, value } = event.target;
    setItem((prevValue) => ({ ...prevValue, [name]: value }));
  }

  function handleAddToCart() {
    let error = "";
    if (item.quantity < 1) {
      error = "Quantity min is 1.";
    } else if (item.quantity > item.product.onStock) {
      error = "Quantity of product is not available.";
    }
    setErrorItem(error);

    if (error !== "") return;

    cartAddItem(item);
    setOpenAddToCart(false);
  }
   
  function handleUpload() {
    imageService
      .upload(image)
      .then((data) => {
        setImageUploaded(true);
        setProduct((prevValue) => ({ ...prevValue, imageId: data }));
      })
      .catch((err) => {
        setErrors({ onSubmit: JSON.parse(err.message).message });
        setSaving(false);
      });
  }

  return (
    <>
      <h2>Products</h2>
      {isAgent && (
        <Button
          onClick={handleAddButton}
          variant={"outlined"}
          color={"primary"}
          style={{ width: "80%", marginBottom: "8px" }}
        >
          Add
        </Button>
      )}
      <ProductList
        products={products}
        isAgent={isAgent}
        cart={cart}
        onEditButton={handleEditButton}
        onDeleteButton={handleDeleteButton}
        onAddToCart={handleAddToCartButton}
      />
      <ProductAddEdit
        open={open}
        form={product}
        saving={saving}
        errors={errors}
        onChange={handleChange}
        onClose={() => setOpen(false)}
        onSubmit={handleSubmit}
        onUpload={handleUpload}
        imageUploaded={imageUploaded}
      />
      <ProductDelete
        open={openDelete}
        product={product}
        saving={saving}
        onClose={() => setOpenDelete(false)}
        onDelete={handleDelete}
      />
      <ProductAddToCart
        open={openAddToCart}
        item={item}
        error={errorItem}
        saving={saving}
        onClose={() => setOpenAddToCart(false)}
        onSubmit={handleAddToCart}
        onChange={handleChangeItem}
      />
    </>
  );
}

ProductManagement.propTypes = {
  isAgent: PropTypes.bool.isRequired,
  cart: PropTypes.array.isRequired,
  cartAddItem: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  return {
    isAgent: state.isAgent,
    cart: state.cart,
  };
}

const mapDispatchToProps = {
  cartAddItem,
};

export default connect(mapStateToProps, mapDispatchToProps)(ProductManagement);
