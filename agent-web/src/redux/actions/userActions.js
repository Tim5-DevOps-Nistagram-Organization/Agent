import * as types from "./actionTypes";
import { apiCallError, beginApiCall } from "./apiStatusActions";

export function loginSuccess() {
  return { type: types.LOGIN_SUCCESS };
}

export function logout() {
  return { type: types.LOGOUT_SUCCESS };
}

export function checkUserIsAgentSuccess(isAgent) {
  return { type: types.CHECK_USER_IS_AGENT_SUCCESS, isAgent };
}

export function login(data) {
  return function (dispatch) {
    dispatch(beginApiCall());
    let isAgent = false;
    if (data.username === "agent" && data.password === "agent") {
      localStorage.setItem("isAgent", "agent");
      isAgent = true;
      dispatch(loginSuccess());
    } else {
      dispatch(apiCallError());
    }
    return isAgent;
  };
}

export function checkUserIsAgent() {
  return function (dispatch) {
    dispatch(beginApiCall());
    let token = localStorage.getItem("isAgent");
    const isAgent = token === "agent";
    dispatch(checkUserIsAgentSuccess(isAgent));
  };
}
