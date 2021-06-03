import { createHeaders, handleError, handleResponse } from "./Utils";

const baseUrl = process.env.REACT_APP_API_GATEWAY_URL + "report/report";

export function getSold() {
  const headers = createHeaders();
  return fetch(baseUrl + "/sold", { method: "GET", headers })
    .then(handleResponse)
    .catch(handleError);
}

export function getEarned() {
  const headers = createHeaders();
  return fetch(baseUrl + "/earned", { method: "GET", headers })
    .then(handleResponse)
    .catch(handleError);
}
