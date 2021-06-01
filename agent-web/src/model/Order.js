export class OrderRequest {
  customerName;
  customerSurname;
  customerAddress;
  items;

  constructor(customerName, customerSurname, customerAddress, items) {
    this.customerName = customerName;
    this.customerSurname = customerSurname;
    this.customerAddress = customerAddress;
    this.items = items;
  }
}

export const newOrderRequest = () => new OrderRequest("", "", "", []);

export class OrderResponse {
  customerName;
  customerSurname;
  customerAddress;
  totalPrice;
  items;

  constructor(
    customerName,
    customerSurname,
    customerAddress,
    totalPrice,
    items
  ) {
    this.customerName = customerName;
    this.customerSurname = customerSurname;
    this.customerAddress = customerAddress;
    this.totalPrice = totalPrice;
    this.items = items;
  }
}
