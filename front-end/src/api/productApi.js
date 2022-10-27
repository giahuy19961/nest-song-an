import adminAxios from "./adminAxios";

export const productApi = {
    getAllProduct: () => {
        return adminAxios.get("/product");
    },

    getProductByPage: async (offset, limit) => {
        try {
            const res = await adminAxios.get(
                `/product/page?page=${offset}&limit=${limit}`
            );
            return res;
        } catch (err) {
            throw err;
        }
    },

    getAllProductByPage: async(offset, limit) => {
        try {
            const res = await adminAxios.get(
                `/product/page/all?page=${offset}&limit=${limit}`
            );
            return res;
        } catch (err) {
            throw err;
        }
    },

    getCountAllProduct: async() => {
        try{
            const res = await adminAxios.get(`/product/count/all`);
            return res
        }catch(err){
            throw err
        }
    },

    getProductById: (id) => {
        return adminAxios.get(`/product/${id}`);
    },
    getCategory: () => {
        return adminAxios.get("/category");
    },
    getProductByFilter: (filter, cateId) => {
        const data = { ...filter, cateId: 0 };
        return adminAxios.post("/product/filter", data);
    },

    // Add new product

    addProductAPI: async(data) => {
        try {
            const res = await adminAxios.put("/product/add",data)
            return res
        } catch (error) {
            throw error
        }
    },

    // Update product 
    updateProductById: async(proiId, proName, proDesc, proPrice ) => {
     
        try{
            const data = { name:proName, description:proDesc, basePrice:proPrice}
            const res = await adminAxios.put(`/product/${proiId}`, data)
            return res
        }catch(err){
            throw err
        }
    },
    //-------------------------

    //update product status

    updateProductStatus: async(proiId, status ) => {
        try{
            const data = { status:status}
            const res = await adminAxios.put(`/product/reactive/${proiId}`, data)
            return res
        }catch(err){
            throw err
        }
    },

    //-------------------------
    //  delete product

    deleteProductById: async(proiId) => {
        try{                  
            const res = await adminAxios.delete(`/product/${proiId}`)
            return res
        }catch(err){
            throw err
        }
    }

    //-------------------------
};
