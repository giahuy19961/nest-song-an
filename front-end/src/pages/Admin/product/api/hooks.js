import { useEffect } from "react";
import { useState } from "react";
import { productApi } from "../../../../api/productApi";


//Products
export const useGetProductsPagination = ({ offset = 1, limit = 10, skipFetch = false }) => {

    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("")

    const getProducts = async (params) => {
        setLoading(true)
        try {
            const { offset, limit } = params;
            const response = await productApi
                .getProductByPage(offset, limit)

            setProducts(response)

        } catch (error) {
            console.log(error)
            if (error.response.status < 500) {
                setError("Không tìm thấy sản phẩm")
                return;
            }
            setError("Internal Server Error")
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        if (!skipFetch) {
            getProducts({ offset, limit });
        }
    }, [offset, limit]);


    return {
        data: products,
        loading,
        error,
    }

}
export const useGetProductsCount = ({ skipFetch = false }) => {

    const [count, setCount] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("")

    const getProductsCount = async () => {
        setLoading(true)
        try {

            const response = await productApi.getCountAllProduct()
            setCount(response)

        } catch (error) {
            if (error.response.status < 500) {
                setError("Không tìm thấy")
                return;
            }
            setError("Internal Server Error")
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        if (!skipFetch) {
            getProductsCount();
        }
    }, []);


    return {
        data: count,
        loading,
        error,
    }

}  


//Meta datas
export const useGetCategories = ({ skipFetch = false }) => {

    const [categories, setCategories] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("")

    const getCategories = async () => {
        setLoading(true)
        try {

            const response = await productApi.getCategory()
            setCategories(response)

        } catch (error) {
            if (error.response.status < 500) {
                setError("Không tìm thấy")
                return;
            }
            setError("Internal Server Error")
        } finally {
            setLoading(false)
        }
    }

    useEffect(() => {
        if (!skipFetch) {
            getCategories();
        }
    }, []);


    return {
        data: categories,
        loading,
        error,
    }

}  

