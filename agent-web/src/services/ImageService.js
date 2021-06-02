import { handleError, handleResponseText } from "./Utils";

const baseUrl = process.env.REACT_APP_API_GATEWAY_URL + "product/image";

export function upload(file: File) {
  const formData = new FormData();
  formData.append("file", file);
  return fetch(baseUrl, { method: "POST", body: formData })
    .then(handleResponseText)
    .catch(handleError);
}
