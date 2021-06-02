import {
  createHeaders,
  handleError,
  handleResponse,
  handleResponseText,
} from "./Utils";

const baseUrl = process.env.REACT_APP_API_GATEWAY_URL + "product/product";

export function getAll() {
  const headers = createHeaders();
  return fetch(baseUrl, { method: "GET", headers })
    .then(handleResponse)
    .catch(handleError);
}

export function get(id) {
  const headers = createHeaders();
  return fetch(baseUrl + "/" + id, { method: "GET", headers })
    .then(handleResponse)
    .catch(handleError);
}

export function create(product) {
  const headers = createHeaders();
  return fetch(baseUrl, {
    method: "POST",
    headers,
    body: JSON.stringify(product),
  })
    .then(handleResponse)
    .catch(handleError);
}

export function update(product) {
  const headers = createHeaders();
  return fetch(baseUrl, {
    method: "PUT",
    headers,
    body: JSON.stringify(product),
  })
    .then(handleResponse)
    .catch(handleError);
}

export function deleteById(id) {
  const headers = createHeaders();
  return fetch(baseUrl + "/" + id, {
    method: "DELETE",
    headers,
  })
    .then(handleResponseText)
    .catch(handleError);
}
