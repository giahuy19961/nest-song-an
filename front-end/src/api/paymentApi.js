import adminAxios from "./adminAxios";

export const paymentApi = {
    getStatus: () => {
        return adminAxios.get("/payment");
    },
};
