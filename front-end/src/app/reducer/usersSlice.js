import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { userApi } from "../../api/userApi";

export const getUsers = createAsyncThunk(
  "users/getUsers",
  async ({ offset, limit }, { rejectWithValue }) => {
    try {
      const usersResponse = await userApi.getUsers({ offset, limit });
      const userCountResponse = await userApi.getUsers();

      return {
        users: usersResponse || [],
        total: userCountResponse.length || 0,
      };
    } catch (error) {
      console.log(error);
      rejectWithValue(error.response?.data);
    }
  }
);

const userSlice = createSlice({
  name: "users",
  initialState: {
    data: null,
    error: null,
    loading: true,
  },
  reducers: {},
  extraReducers: {
    [getUsers.pending]: (state, action) => {
      state.loading = true;
    },
    [getUsers.fulfilled]: (state, action) => {
      state.loading = false;
      state.data = action.payload;
      state.err = null;
    },
    [getUsers.rejected]: (state, action) => {
      state.loading = false;
      state.data = null;
      state.err = action.payload;
    },
  },
});

const usersReducer = userSlice.reducer;

export default usersReducer;
