import { combineReducers } from "redux";
import isAgent from "./isAgentReducer";

const rootReducer = combineReducers({
  isAgent,
});

export default rootReducer;
