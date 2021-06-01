import * as types from "../actions/actionTypes";
import initialState from "./initialState";

export default function isAgentReducer(state = initialState.isAgent, action) {
  switch (action.type) {
    case types.LOGIN_SUCCESS:
      return true;
    case types.LOGOUT_SUCCESS:
      localStorage.setItem("isAgent", null);
      localStorage.clear();
      return false;
    case types.CHECK_USER_IS_AGENT:
      return action.isAgent;
    default:
      return state;
  }
}
