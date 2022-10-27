import adminAxios from "./adminAxios";

export const accountApi = {
    getAllAccount: () => {
        return adminAxios.get("/user");
    },
};
