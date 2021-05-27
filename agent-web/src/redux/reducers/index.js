import { combineReducers } from "redux";
import isAgent from "./isAgentReducer";
import apiCallStatus from "./apiStatusReducer";

const rootReducer = combineReducers({
    isAgent,
    apiCallStatus
});

export default rootReducer;