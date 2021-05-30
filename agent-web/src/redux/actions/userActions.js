import * as types from "./actionTypes";

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
    let isAgent = false;
    if (data.username === "agent" && data.password === "agent") {
      localStorage.setItem("isAgent", "agent");
      isAgent = true;
      dispatch(loginSuccess());
    }
    return isAgent;
  };
}

export function checkUserIsAgent() {
  return function (dispatch) {
    let token = localStorage.getItem("isAgent");
    const isAgent = token === "agent";
    dispatch(checkUserIsAgentSuccess(isAgent));
  };
}
