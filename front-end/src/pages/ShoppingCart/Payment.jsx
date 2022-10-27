import { faLocationDot, faPhone } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { InputLabel, MenuItem, Select } from "@mui/material";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { billApi } from "../../api/billApi";
import { paymentApi } from "../../api/paymentApi";
import { Loading } from "../../components/Loading/Loading";
import {
    CART_LOADING_REQUEST,
    CART_PAYING_SUCCESS,
    selectCart,
} from "../../features/cart/cartSlice";
import {
    convertPriceToString,
    getErrorMessageFromServer,
} from "../../utils/serverUtils";

export const Payment = ({ setStep }) => {
    const [paymentList, setPaymentList] = useState([]);
    const [method, setMethod] = useState(1);
    const cart = useSelector(selectCart);
    const dispatch = useDispatch();
    const handlePay = () => {
        const pay = async () => {
            dispatch(CART_LOADING_REQUEST());
            const response = await billApi.payment({
                ...cart.shipInfor,
                paymentStatusCodeId: method,
            });
            dispatch(CART_PAYING_SUCCESS());
            setStep("receipt");
            console.log(response);
        };
        pay();
    };
    useEffect(() => {
        const FetchPaymentList = async () => {
            try {
                const response = await paymentApi.getStatus();
                setPaymentList(response);
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                // dispatch(ORDER_LOADING_FAIL(errorMessage));
            }
        };
        FetchPaymentList();
    }, []);

    const handleChange = (event) => {
        setMethod(event.target.value);
    };

    return (
        <>
            {cart.loading ? (
                <Loading />
            ) : (
                <div className="mt-10 w-9/12 m-auto">
                    <div className="text-center text-4xl font-semibold">
                        Thanh toán
                    </div>
                    <div className="text-center py-10 text-lg">
                        <div>
                            <FontAwesomeIcon icon={faLocationDot} /> Địa chỉ
                            nhận hàng: {cart.shipInfor.address}
                        </div>
                        <div>
                            <FontAwesomeIcon icon={faPhone} /> Số điện thoại
                            người nhận: {cart.shipInfor.phoneNumber}
                        </div>
                    </div>
                    <div className="mr-0 ml-auto w-fit text-xl font-semibold">
                        <span className=" ">Tổng tiền:</span>{" "}
                        <span className="text-orange-500">
                            {convertPriceToString(cart.totalPrice)} &#8363;
                        </span>
                    </div>
                    <div className="border-2 flex gap-2 mt-2 mb-4 p-2 w-fit mr-0 ml-auto items-center">
                        <p className="px-5 font-semibold">
                            Phương thức thanh toán
                        </p>
                        <Select
                            className="h-10 px-2 border-l-2 text-emerald-500"
                            onChange={handleChange}
                        >
                            <MenuItem value={1}>Tiền mặt</MenuItem>
                            <MenuItem value={2}>Paypal</MenuItem>
                        </Select>
                    </div>
                    <div className="flex justify-between pb-16">
                        <div
                            className="border-slate-300 border-2 rounded-3xl w-60 h-12 text-regal-blue cursor-pointer flex justify-center items-center"
                            onClick={() => setStep("delivery")}
                        >
                            Quay lại
                        </div>
                        <button
                            className="bg-regal-blue rounded-3xl w-60 h-12 text-white shadow-md"
                            onClick={() => handlePay()}
                        >
                            Thanh toán
                        </button>
                    </div>
                </div>
            )}
        </>
    );
};
