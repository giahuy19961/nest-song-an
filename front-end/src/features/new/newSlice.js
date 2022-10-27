import { createSlice } from "@reduxjs/toolkit";

const initialState = {
    news: [],
    new: {},
};

export const newSlice = createSlice({
    name: "news",
    initialState,
    reducers: {
        NEWS_LOADING_REQUEST: (state) => {
            state.loading = true;
        },
        NEWS_LOADING_ALL_SUCCESS: (state, action) => {
            state.loading = false;
            state.news = action.payload;
        },
        NEWS_LOADING_FAIL: (state, action) => {
            state.newErrorMessage = action.payload;
            state.loading = false;
        },
        NEWS_LOADING_ONE_SUCCESS: (state, action) => {
            state.new = action.payload;
            state.loading = false;
        },
    },
});

export const {
    NEWS_LOADING_REQUEST,
    NEWS_LOADING_ALL_SUCCESS,
    NEWS_LOADING_FAIL,
    NEWS_LOADING_ONE_SUCCESS,
} = newSlice.actions;

export const selectNew = (state) => state.news;

export default newSlice.reducer;
