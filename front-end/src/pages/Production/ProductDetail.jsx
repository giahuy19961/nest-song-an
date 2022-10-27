import { useEffect } from "react";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";

import { useNavigate, useParams } from "react-router-dom";
import { productApi } from "../../api/productApi";
import { Loading } from "../../components/Loading/Loading";
import {
    CART_ADDING_SUCCESS,
    CART_LOADING_FAIL,
    CART_LOADING_REQUEST,
    selectCart,
} from "../../features/cart/cartSlice";
import {
    PRODUCT_LOADING_FAIL,
    PRODUCT_LOADING_ONE,
    PRODUCT_LOADING_REQUEST,
    selectProduct,
} from "../../features/production/productSlice";
import {
    convertPriceToString,
    getErrorMessageFromServer,
} from "../../utils/serverUtils";
import { billApi } from "../../api/billApi";
import { selectUser } from "../../features/user/userSlice";
import { Alert, Slide } from "@mui/material";
import { theme } from "../../assets/theme";
import { faCartPlus, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

export const ProductDetail = () => {
    const dispatch = useDispatch();
    const [productAmount, setProductAmount] = useState(1);
    const products = useSelector(selectProduct);
    const cart = useSelector(selectCart);
    const user = useSelector(selectUser);
    const product = products.product;
    const productId = useParams();
    const navigate = useNavigate();
    const [productImage, setProductImage] = useState("");
    const [alert, setAlert] = useState(false);

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                dispatch(PRODUCT_LOADING_REQUEST());
                const response = await productApi.getProductById(productId.id);
                dispatch(PRODUCT_LOADING_ONE(response));
                setProductImage(response.listImages[0].imgPath);
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(PRODUCT_LOADING_FAIL(errorMessage));
            }
        };
        fetchProduct();
    }, [productId.id]);

    const handleAddToCart = async () => {
        try {
            dispatch(CART_LOADING_REQUEST());
            const response = await billApi.addToCart(
                user.userInfor.id,
                product,
                productAmount
            );
            localStorage.setItem("cart", response);

            dispatch(CART_ADDING_SUCCESS(response));
            setAlert(true);
            setTimeout(() => {
                setAlert(false);
            }, 2000);
        } catch (error) {
            const errorMessage = getErrorMessageFromServer(error);
            dispatch(CART_LOADING_FAIL(errorMessage));
        }
    };

    return (
        <>
            {products.loading || cart.loading ? (
                <Loading />
            ) : products.productErrorMessage === 404 ? (
                navigate("*")
            ) : (
                <div className="bg-gray-100 absolute h-auto">
                    <div className="flex bg-white p-10 m-20 mb-6 relative">
                        <Slide direction="up" in={alert}>
                            <Alert
                                severity="success"
                                color="success"
                                sx={{
                                    position: "fixed",
                                    bottom: 50,
                                    right: 50,
                                }}
                            >
                                Thêm vào giỏ hàng thành công
                            </Alert>
                        </Slide>
                        <div className="w-[60%] px-14">
                            <img
                                src={productImage}
                                alt={product.name}
                                className="w-full h-[28rem] shadow-lg shadow-gray-400"
                            />
                            <div className="flex justify-center gap-4 mt-4">
                                {Object.keys(product).length === 0
                                    ? ""
                                    : product.listImages.map((item, index) => (
                                          <img
                                              src={item.imgPath}
                                              className="w-24 cursor-pointer border-2 rounded-md"
                                              onMouseOver={() =>
                                                  setProductImage(item.imgPath)
                                              }
                                              key={index}
                                          />
                                      ))}
                            </div>
                        </div>

                        <div className="w-[40%] flex flex-col justify-between">
                            <div>
                                <h1 className="text-3xl font-semibold font-verda">
                                    {product.name}
                                </h1>
                                <p className="text-orange-500 font-trebu font-semibold text-lg mt-10">
                                    Giá:{" "}
                                    {convertPriceToString(
                                        product.basePrice -
                                            product.basePrice * product.deal
                                    )}{" "}
                                    VNĐ{" "}
                                    {product.deal === 0 || (
                                        <span className="text-gray-400/60 text-base font-thin line-through ml-4">
                                            {convertPriceToString(
                                                product.basePrice
                                            )}{" "}
                                            VNĐ
                                        </span>
                                    )}
                                </p>
                                <div className="flex mt-3">
                                    <p className="w-11/12 text-gray-500">
                                        {product.description}
                                    </p>
                                </div>
                            </div>
                            <div className="mb-20">
                                <p className="text-green-400 mt-10 font-medium">
                                    Sản phẩm còn lại: {product.quantity}
                                </p>
                                <div className="mt-2 mb-1 flex">
                                    <div className="flex">
                                        <button
                                            className="px-4 border text-sm flex items-center rounded-tl-3xl rounded-bl-3xl hover:bg-gray-100"
                                            onClick={() => {
                                                if (!productAmount)
                                                    return setProductAmount(1);
                                                if (productAmount > 1)
                                                    return setProductAmount(
                                                        (pre) => pre - 1
                                                    );
                                            }}
                                        >
                                            <FontAwesomeIcon icon={faMinus} />
                                        </button>
                                        <input
                                            value={productAmount}
                                            type="number"
                                            className="w-12 text-center border font-medium"
                                            onChange={(e) => {
                                                const number = Number.parseInt(
                                                    e.target.value
                                                );
                                                if (number > product.quantity)
                                                    number = product.quantity;
                                                if (number < 1) number = 1;
                                                return setProductAmount(number);
                                            }}
                                            min="1"
                                            max={product.quantity}
                                            required
                                        />
                                        <button
                                            className="px-4 border text-sm flex items-center rounded-tr-3xl rounded-br-3xl hover:bg-gray-100"
                                            onClick={() => {
                                                if (!productAmount)
                                                    return setProductAmount(1);
                                                if (
                                                    productAmount <
                                                    product.quantity
                                                )
                                                    return setProductAmount(
                                                        (pre) => pre + 1
                                                    );
                                            }}
                                        >
                                            <FontAwesomeIcon icon={faPlus} />
                                        </button>
                                    </div>
                                    <button
                                        className="p-4 px-5 rounded-full bg-regal-blue text-white ml-14 text-xs uppercase font-medium hover:bg-regal-blue/80 flex items-center gap-2"
                                        onClick={handleAddToCart}
                                    >
                                        <FontAwesomeIcon
                                            icon={faCartPlus}
                                            className="w-5 h-5"
                                        />{" "}
                                        Thêm vào giỏ hàng
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};
