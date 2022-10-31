import { configureStore, createReducer } from "@reduxjs/toolkit";
import filterReducer from "../features/production/filterSlice";
import productReducer from "../features/production/productSlice";
import userReducer from "../features/user/userSlice";
import newReducer from "../features/new/newSlice";
import cartReducer from "../features/cart/cartSlice";
import orderReducer from "../features/order/orderSlice";
import usersReducer from "./reducer/usersSlice";

export const store = configureStore({
  reducer: {
    filter: filterReducer,
    user: userReducer,
    products: productReducer,
    news: newReducer,
    cart: cartReducer,
    order: orderReducer,
    users: usersReducer,
  },
});
