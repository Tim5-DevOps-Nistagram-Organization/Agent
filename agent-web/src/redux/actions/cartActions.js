import * as types from "./actionTypes";

export function cartAddItem(item) {
  return { type: types.CART_ADD_ITEM, item };
}

export function cartRemoveItem(item) {
  return { type: types.CART_REMOVE_ITEM, item };
}

export function cartEmpty() {
  return { type: types.CART_EMPTY };
}

export function cartCheck() {
  return { type: types.CHECK_CART };
}
