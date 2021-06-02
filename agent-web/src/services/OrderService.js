import { createHeaders, handleError, handleResponse } from "./Utils";

const baseUrl = process.env.REACT_APP_API_GATEWAY_URL + "order/order";

export function create(order) {
  console.log(order);
  const headers = createHeaders();
  return fetch(baseUrl, {
    method: "POST",
    headers,
    body: JSON.stringify(order),
  })
    .then(handleResponse)
    .catch(handleError);
}
