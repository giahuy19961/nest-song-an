import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    name: [],
    lowPrice: 0,
    highPrice: 50000000,
};

export const filterSlice = createSlice({
    name: "filter",
    initialState,
    reducers: {
        FILTER_ADD_NAME: (state, action) => {
            state.name.push(action.payload);
        },
        FILTER_REMOVE_NAME: (state, action) => {
            let index = state.name.indexOf(action.payload);
            state.name.splice(index, 1);
        },
        FILTER_ADD_RANGE_PRICE: (state, action) => {
            state.lowPrice = action.payload[0] * 1000;
            state.highPrice = action.payload[1] * 1000;
        },
    },
});

export const { FILTER_ADD_NAME, FILTER_REMOVE_NAME, FILTER_ADD_RANGE_PRICE } =
    filterSlice.actions;

export const selectFilter = (state) => state.filter;

export default filterSlice.reducer;
