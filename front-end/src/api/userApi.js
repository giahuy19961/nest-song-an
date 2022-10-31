import axios from "axios";
import adminAxios from "./adminAxios";

export const userApi = {
  login: (input) => {
    return adminAxios.post("user/login", input);
  },
  signUp: (input) => {
    return adminAxios.post("user/insert", input);
  },
  getUserInfor: (id) => {
    const token = localStorage.getItem("token");
    const data = { id: id, token: token };
    return adminAxios.get(`user/${id}`, data);
  },
  getUsers: (params) => {
    return adminAxios.get(
      `user?offset=${params?.offset || ""}&limit=${params?.limit || ""}`
    );
  },
  createUser: (user) => {
    return adminAxios.post("user/insert", user);
  },
};
