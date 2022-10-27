import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Loading } from "../../components/Loading/Loading";
import { billApi } from "../../api/billApi";
import {
    ORDER_LOADING,
    ORDER_LOADING_FAIL,
    ORDER_LOADING_SUCCESS,
    selectOrder,
} from "../../features/order/orderSlice";
import { selectUser } from "../../features/user/userSlice";
import { getErrorMessageFromServer } from "../../utils/serverUtils";
import { OrderCard } from "./OrderCard";

export const Order = () => {
    const user = useSelector(selectUser);
    const order = useSelector(selectOrder);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        const FetchOrder = async () => {
            try {
                dispatch(ORDER_LOADING());
                const response = await billApi.getOrder(user.userInfor.id);
                console.log(response);
                dispatch(ORDER_LOADING_SUCCESS(response));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(ORDER_LOADING_FAIL(errorMessage));
            }
        };
        if (user.token) FetchOrder();
        else navigate("/sign-in");
    }, [user]);

    return (
        <>
            {order.loading ? (
                <Loading />
            ) : order.data === null ? (
                <div className="text-2xl font-medium flex justify-center items-center flex-col h-screen">
                    <div
                        style={{
                            backgroundImage:
                                "url(https://deo.shopeemobile.com/shopee/shopee-pcmall-live-sg/assets/5fafbb923393b712b96488590b8f781f.png)",
                            backgroundRepeat: "no-repeat",
                            backgroundPosition: "50%",
                            backgroundSize: "contain",
                            width: "100px",
                            height: "100px",
                        }}
                    ></div>
                    <div className="mt-4">Bạn không có đơn hàng nào</div>
                </div>
            ) : (
                <div className="p-20 bg-gray-100">
                    <div className="text-center text-4xl font-bold mt-10">
                        Đơn hàng đã mua
                    </div>
                    {order.data.map((card) => (
                        <OrderCard card={card} />
                    ))}
                </div>
            )}
        </>
    );
};
