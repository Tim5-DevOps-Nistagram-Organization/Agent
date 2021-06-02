export class Product {
  id;
  name;
  price;
  onStock;
  imageId;

  constructor(id, name, price, onStock, imageId) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.onStock = onStock;
    this.imageId = imageId;
  }
}

export const newProduct = () => new Product(null, "", 0, 0, 0);
