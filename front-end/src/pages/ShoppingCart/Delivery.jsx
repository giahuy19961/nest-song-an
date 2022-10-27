import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectUser } from "../../features/user/userSlice";
import { userApi } from "../../api/userApi";
import { useState } from "react";
import {
    CART_ADDING_INFORMATION,
    selectCart,
} from "../../features/cart/cartSlice";
import { Input } from "../../components/Input/Input";
export const Delivery = ({ setStep, firstRender }) => {
    const user = useSelector(selectUser);
    const cart = useSelector(selectCart);
    const dispatch = useDispatch();
    const [userInfo, setUserInfo] = useState({});

    useEffect(() => {
        const fetchInfor = async () => {
            try {
                const response = await userApi.getUserInfor(user.userInfor.id);
                setUserInfo(response);
            } catch (error) {
                if (error.response) console.log(error.response.data);
            }
        };
        if (firstRender.current) {
            firstRender.current = false;
            fetchInfor();
        } else setUserInfo(cart.shipInfor);
    }, []);

    const handleSubmit = () => {
        dispatch(
            CART_ADDING_INFORMATION({
                id: userInfo.id,
                address: userInfo.address,
                phoneNumber: userInfo.phoneNumber,
            })
        );
    };

    return (
        <div className="mt-10 w-9/12 m-auto">
            <div className="text-center text-4xl font-semibold">
                Thông tin vận chuyển
            </div>
            <div className="mt-10 grid-rows-2 gap-5 grid mb-20">
                <Input
                    type="number"
                    label="Số điện thoại"
                    value={userInfo.phoneNumber}
                    pattern="[0-9]{10}"
                    required
                    variant="outlined"
                    handleChange={(value) =>
                        setUserInfo((pre) => ({
                            ...pre,
                            phoneNumber: value,
                        }))
                    }
                />
                <Input
                    type="text"
                    label="Địa chỉ giao hàng"
                    value={userInfo.address}
                    required
                    variant="outlined"
                    handleChange={(value) =>
                        setUserInfo((pre) => ({
                            ...pre,
                            address: value,
                        }))
                    }
                />
            </div>
            <div className="flex justify-between pb-16">
                <div
                    className="border-slate-300 border-2 rounded-3xl w-60 h-12 text-regal-blue cursor-pointer flex justify-center items-center"
                    onClick={() => setStep("cart")}
                >
                    Quay lại giỏ hàng
                </div>
                <button
                    className="bg-regal-blue rounded-3xl w-60 h-12 text-white shadow-md"
                    onClick={() => {
                        handleSubmit();
                        setStep("payment");
                    }}
                >
                    Thanh toán
                </button>
            </div>
        </div>
    );
};
