import { createSlice } from "@reduxjs/toolkit";
import { IAuthentication } from "../../../utils/interfaces";

const initialState: IAuthentication = {
  success: false,
  jwtToken: "",
  user: {
    id: "",
    firstName: "",
    lastName: "",
    username: "",
    email: "",
    role: "",
    birthday: null,
    bio: "",
    createdAt: null,
  },
};

export const counterSlice = createSlice({
  name: "authenticate",
  initialState,
  reducers: {
    login: (state, action) => {
      state.success = action.payload.success;
      state.jwtToken = action.payload.token;
      state.user = action.payload.userResDto;
    },
    logout: (state) => {
      (state.success = false),
        (state.jwtToken = ""),
        (state.user = {
          id: "",
          firstName: "",
          lastName: "",
          username: "",
          email: "",
          role: "",
          birthday: null,
          bio: "",
          createdAt: null,
        });
    },
  },
});

export const { login, logout } = counterSlice.actions;

export default counterSlice.reducer;
