import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Loading } from "../../components/Loading/Loading";
import {
    CART_LOADING_FAIL,
    CART_LOADING_REQUEST,
    CART_LOADING_SUCCESS,
    CART_TOTAL_UPDATE,
    CART_UPDATING_SUCCESS,
    selectCart,
} from "../../features/cart/cartSlice";
import { selectUser } from "../../features/user/userSlice";
import { billApi } from "../../api/billApi";
import {
    convertPriceToString,
    getErrorMessageFromServer,
} from "../../utils/serverUtils";
import { CartItem } from "../../components/Cart/CartItem";
import { Skeleton } from "@mui/material";
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

export const Cart = ({ setStep }) => {
    const [alert, setAlert] = useState(false);
    const navigate = useNavigate();
    const cart = useSelector(selectCart);
    const dispatch = useDispatch();
    const user = useSelector(selectUser);
    const token = localStorage.getItem("token");
    let totalPrice = 0;

    console.log(cart.cart);

    useEffect(() => {
        const fetchCart = async () => {
            try {
                dispatch(CART_LOADING_REQUEST());
                const response = await billApi.getCart(user.userInfor.id);
                localStorage.setItem("cart", response[0]);
                dispatch(CART_LOADING_SUCCESS(response[0]));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(CART_LOADING_FAIL(errorMessage));
            }
        };
        fetchCart();
    }, [token]);

    const handleChangeAmount = async (
        id,
        productAmount,
        billId,
        setLoading
    ) => {
        try {
            const response = await billApi.updateQuantity(
                user.userInfor.id,
                id,
                productAmount,
                billId
            );
            console.log(response);
            dispatch(CART_UPDATING_SUCCESS(response));
            setLoading(false);
        } catch (error) {
            const errorMessage = getErrorMessageFromServer(error);
            dispatch(CART_LOADING_FAIL(errorMessage));
        }
    };

    const handleDelete = async (item, setLoading) => {
        try {
            const response = await billApi.removeFromCart(
                user.userInfor.id,
                item.id
            );
            console.log(response);
            dispatch(CART_UPDATING_SUCCESS(response));
            setLoading(false);
        } catch (error) {
            const errorMessage = getErrorMessageFromServer(error);
            dispatch(CART_LOADING_FAIL(errorMessage));
        }
    };
    return (
        <>
            {alert && (
                <Dialog open={alert} onClose={() => setAlert(false)}>
                    <DialogTitle id="alert-dialog-title">
                        {"GI??? H??NG TR???NG"}
                    </DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            Kh??ng th??? thanh to??n khi gi??? h??ng tr???ng. Xin th???
                            l???i!
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button
                            onClick={() =>
                                setAlert(false, navigate("/production"))
                            }
                            autoFocus
                        >
                            S???n ph???m
                        </Button>
                        <Button onClick={() => setAlert(false)}>????ng</Button>
                    </DialogActions>
                </Dialog>
            )}
            {cart.loading ? (
                <Loading />
            ) : (
                <div className="mt-10">
                    <div className="w-9/12 m-auto">
                        <ul className="grid grid-cols-12 grid-flow-row-dense text-center uppercase font-bold text-xs text-zinc-500 border-b-2 pb-1">
                            <li className="col-span-5 justify-self-start">
                                S???n ph???m
                            </li>
                            <li className="col-span-2">Lo???i</li>
                            <li className="col-span-2">S??? l?????ng</li>
                            <li className="col-span-2">gi??</li>
                            <li className="invisible col-span-1">delete</li>
                        </ul>
                        {cart.cart.listBillDetails?.length === 0 ? (
                            <div className="font-medium text-zinc-500 text-xl mt-10 text-center w-full">
                                Gi??? h??ng tr???ng
                            </div>
                        ) : (
                            cart.cart.listBillDetails?.map((item, index) => {
                                totalPrice += item.price;
                                return (
                                    <div className="" key={index}>
                                        {cart.loading ? (
                                            <Skeleton />
                                        ) : (
                                            <CartItem
                                                item={item}
                                                handleChangeAmount={
                                                    handleChangeAmount
                                                }
                                                handleDelete={handleDelete}
                                            />
                                        )}
                                        <div className="border-t-2 w-full border-gray-200" />
                                    </div>
                                );
                            })
                        )}
                    </div>

                    <div className="w-9/12 m-auto">
                        <div className="flex items-center justify-end">
                            <div className="font-verda text-[0.93rem] font-semibold mr-10 text-zinc-500">
                                T???ng:{" "}
                                <span className="font-semibold text-red-500">
                                    {convertPriceToString(totalPrice)}{" "}
                                    <span className="text-xs">&#8363;</span>
                                </span>
                            </div>
                            <div
                                className="my-10 px-10 py-2.5 text-center bg-[#00ADB5] text-white shadow-md cursor-pointer rounded-full"
                                onClick={() => {
                                    if (cart.cart.listBillDetails.length === 0)
                                        setAlert(true);
                                    else {
                                        dispatch(CART_TOTAL_UPDATE(totalPrice));
                                        setStep("delivery");
                                    }
                                }}
                            >
                                Mua h??ng
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};
