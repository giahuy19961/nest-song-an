import adminAxios from "./adminAxios";

export const newApi = {
    getAllNew: () => {
        return adminAxios.get("/news");
    },
    getNewById: (id) => {
        return adminAxios.get(`/news/${id}`);
    },
};
