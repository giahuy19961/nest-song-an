import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    products: [],
    product: {},
};

export const productSlice = createSlice({
    name: "products",
    initialState,
    reducers: {
        PRODUCT_LOADING_REQUEST: (state) => {
            state.loading = true;
        },
        PRODUCT_LOADING_BY_PAGE_SUCCESS: (state, action) => {
            state.products = action.payload;
            state.loading = false;
        },
        PRODUCT_LOADING_FAIL: (state, action) => {
            state.productErrorMessage = action.payload;
            state.products = [];

            state.loading = false;
        },
        PRODUCT_LOADING_ONE: (state, action) => {
            state.product = action.payload;
            state.loading = false;
        },
    },
});

export const {
    PRODUCT_LOADING_REQUEST,
    PRODUCT_LOADING_BY_PAGE_SUCCESS,
    PRODUCT_LOADING_FAIL,
    PRODUCT_LOADING_ONE,
} = productSlice.actions;

export const selectProduct = (state) => state.products;

export default productSlice.reducer;
