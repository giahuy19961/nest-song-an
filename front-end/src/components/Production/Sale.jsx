import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { productApi } from "../../api/productApi";
import {
    PRODUCT_LOADING_BY_PAGE_SUCCESS,
    PRODUCT_LOADING_FAIL,
    PRODUCT_LOADING_REQUEST,
    selectProduct,
} from "../../features/production/productSlice";
import { getErrorMessageFromServer } from "../../utils/serverUtils";
import { Carousel } from "../Carousel/Carousel";
import { Loading } from "../Loading/Loading";

export const Sale = () => {
    const products = [...useSelector(selectProduct).products];
    const dispatch = useDispatch();

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                dispatch(PRODUCT_LOADING_REQUEST());
                const response = await productApi.getAllProduct();
                localStorage.setItem("products", response);
                dispatch(PRODUCT_LOADING_BY_PAGE_SUCCESS(response));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(PRODUCT_LOADING_FAIL(errorMessage));
            }
        };
        fetchProduct();
    }, []);

    let saleProduct = [];
    if (products.length !== 0)
        saleProduct = products.sort((a, b) => b.deal - a.deal).slice(0, 6);

    const salePrice = (item) => item.basePrice - item.basePrice * item.deal;

    return (
        <>{products.loading ? <Loading /> : <Carousel data={saleProduct} />}</>
    );
};
