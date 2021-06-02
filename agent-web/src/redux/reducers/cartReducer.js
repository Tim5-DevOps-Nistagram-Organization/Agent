import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function booksReducer(state = initialState.cart, action) {
  let newState;
  switch (action.type) {
    case types.CART_ADD_ITEM:
      newState = [...state, action.item];
      localStorage.setItem("cart", JSON.stringify(newState));
      return newState;
    case types.CART_REMOVE_ITEM:
      newState = state.filter(
        ({ product }) => product.id !== action.item.product.id
      );
      localStorage.setItem("cart", JSON.stringify(newState));
      return newState;
    case types.CART_EMPTY:
      localStorage.setItem("cart", JSON.stringify([]));
      return [];
    case types.CHECK_CART:
      const cart = localStorage.getItem("cart");
      return cart ? JSON.parse(cart) : [];
    default:
      return state;
  }
}
