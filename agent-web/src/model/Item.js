import { newProduct } from "./Product";

export class Item {
  product;
  quantity;

  constructor(product, quantity) {
    this.product = product;
    this.quantity = quantity;
  }
}

export const newItem = () => new Item(newProduct(), 0);

export class ItemRequest {
  productId;
  quantity;

  constructor(productId, quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }
}

export class ItemResponse {
  productName;
  productPrice;
  quantity;

  constructor(productName, productPrice, quantity) {
    this.productName = productName;
    this.productPrice = productPrice;
    this.quantity = quantity;
  }
}
