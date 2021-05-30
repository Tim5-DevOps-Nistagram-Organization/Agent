export class Product {
  id;
  name;
  price;
  onStock;
  image;

  constructor(id, name, price, onStock, image) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.onStock = onStock;
    this.image = image;
  }
}

export const newProduct = () => new Product(null, "", 0, 0, "");
