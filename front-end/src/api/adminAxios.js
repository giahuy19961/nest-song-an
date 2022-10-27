import axios from "axios";

const adminAxios = axios.create({
    baseURL: process.env.REACT_APP_API_URL,
    headers: {
        Accept: "application/json",
        "Content-Type": "application/json;charset=UTF-8",
        // Accept: "*/*",
        // "Content-Type": "text/html; charset=UTF-8",
        // "Content-Type": "multipart/form-data; boundary=something",
    },
});

adminAxios.interceptors.response.use(
    (response) => {
        if (response && response.data) {
            return response.data;
        }
        return response;
    },
    (error) => {
        throw error;
    }
);

export default adminAxios;
