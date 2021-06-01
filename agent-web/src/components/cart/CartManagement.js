import React, { useState } from "react";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import { cartRemoveItem, cartEmpty } from "../../redux/actions/cartActions";
import CartList from "./CartList";
import { newOrderRequest, OrderResponse } from "../../model/Order";
import CartForm from "./CartForm";
import { ItemRequest, ItemResponse } from "../../model/Item";
import ShowOrder from "./ShowOrder";

function CartManagement({ cart, cartRemoveItem, cartEmpty }) {
  const [order, setOrder] = useState(newOrderRequest);
  const [saving, setSaving] = useState(false);
  const [errors, setErrors] = useState({});
  const [ordered, setOrdered] = useState(null);

  function handleChange(event) {
    const { name, value } = event.target;
    setOrder((prevValue) => ({ ...prevValue, [name]: value }));
  }

  function formIsValid() {
    const { customerName, customerSurname, customerAddress } = order;
    const errors = {};

    if (!customerName) errors.customerName = "Name is required.";
    if (!customerSurname) errors.customerSurname = "Surname is required.";
    if (!customerAddress) errors.customerAddress = "Address is required.";

    setErrors(errors);
    return Object.keys(errors).length === 0;
  }

  function handleSubmit() {
    if (!formIsValid()) return;
    setSaving(true);
    console.log("create order");
    const items = [];
    const itemsOrdered = [];
    let sum = 0;
    for (const item of cart) {
      items.push(new ItemRequest(item.product.id, item.quantity));
      itemsOrdered.push(
        new ItemResponse(
          item.product.name,
          item.product.price * item.quantity,
          item.quantity
        )
      );
      console.log(itemsOrdered);
      sum += item.product.price * item.quantity;
    }
    setOrder((prevValue) => ({ ...prevValue, items }));
    setSaving(false);
    cartEmpty();
    // error onSubmit
    setOrdered(
      new OrderResponse(
        order.customerName,
        order.customerSurname,
        order.customerAddress,
        sum,
        itemsOrdered
      )
    );
  }

  return (
    <div style={{ width: "80%", marginLeft: "10%" }}>
      {cart.length > 0 ? (
        <>
          {ordered !== null ? "cao" : "mrs"}
          <h2>Cart</h2>
          <CartList
            onEmptyCart={() => cartEmpty()}
            onRemoveFromCart={(id) => cartRemoveItem(id)}
            cart={cart}
          />
          <CartForm
            form={order}
            errors={errors}
            saving={saving}
            onSubmit={handleSubmit}
            onChange={handleChange}
          />
        </>
      ) : ordered !== null ? (
        <ShowOrder order={ordered} />
      ) : (
        <h2>Cart is empty</h2>
      )}
    </div>
  );
}

CartManagement.propTypes = {
  cart: PropTypes.array.isRequired,
  cartRemoveItem: PropTypes.func.isRequired,
  cartEmpty: PropTypes.func.isRequired,
};

function mapStateToProps(state) {
  return {
    cart: state.cart,
  };
}

const mapDispatchToProps = {
  cartRemoveItem,
  cartEmpty,
};

export default connect(mapStateToProps, mapDispatchToProps)(CartManagement);
