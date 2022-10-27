import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    cart: { listBillDetails: [] },
    shipInfor: {},
};

export const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        CART_LOADING_REQUEST: (state) => {
            state.loading = true;
        },
        CART_LOADING_SUCCESS: (state, action) => {
            state.cart = action.payload;
            state.loading = false;
        },
        CART_LOADING_FAIL: (state, action) => {
            state.cartErrorMessage = action.payload;
            state.cart = { listBillDetails: [] };
            state.loading = false;
        },
        CART_TOTAL_UPDATE: (state, action) => {
            state.totalPrice = action.payload;
        },
        CART_ADDING_SUCCESS: (state, action) => {
            state.loading = false;
            state.cart.listBillDetails = action.payload;
        },
        CART_UPDATING_SUCCESS: (state, action) => {
            state.cart.listBillDetails = action.payload;
            state.loading = false;
        },
        CART_ADDING_INFORMATION: (state, action) => {
            state.shipInfor = action.payload;
        },
        CART_PAYING_SUCCESS: (state) => {
            state.cart = { listBillDetails: [] };
            state.loading = false;
        },
    },
   
});

export const {
    CART_LOADING_REQUEST,
    CART_LOADING_SUCCESS,
    CART_LOADING_FAIL,
    CART_TOTAL_UPDATE,
    CART_ADDING_SUCCESS,
    CART_UPDATING_SUCCESS,
    CART_ADDING_INFORMATION,
    CART_PAYING_SUCCESS,
} = cartSlice.actions;

export const selectCart = (state) => state.cart;

export default cartSlice.reducer;
