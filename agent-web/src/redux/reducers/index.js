import { combineReducers } from "redux";
import isAgent from "./isAgentReducer";
import cart from "./cartReducer";

const rootReducer = combineReducers({
  isAgent,
  cart,
});

export default rootReducer;
