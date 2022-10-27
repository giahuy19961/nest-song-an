import { useEffect, useRef, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { selectUser } from "../../features/user/userSlice";
import { Cart } from "./Cart";
import { Delivery } from "./Delivery";
import { Payment } from "./Payment";
import { Receipt } from "./Receipt";

export const ShoppingCart = () => {
    const [step, setStep] = useState("cart");
    const user = useSelector(selectUser);
    const navigate = useNavigate();
    const firstRender = useRef(true);
    useEffect(() => {
        if (!user.token) navigate("/sign-in");
    }, []);
    // console.log(step);
    return (
        <>
            <div className="bg-gray-100 absolute w-full">
                <div className="bg-white shadow-lg shadow-gray-200 m-24">
                    <div className="py-10">
                        <div className="flex justify-between w-9/12 text-[10px] font-semibold font-verda uppercase border-2 rounded-full text-center text-black m-auto">
                            <div
                                className={`rounded-full w-full p-2 pt-2.5 ${
                                    step === "cart"
                                        ? "bg-regal-blue text-white"
                                        : ""
                                }`}
                            >
                                Giỏ hàng
                            </div>
                            <div
                                className={`rounded-full w-full p-2 pt-2.5 ${
                                    step === "delivery"
                                        ? "bg-regal-blue text-white"
                                        : ""
                                }`}
                            >
                                Thông tin
                            </div>
                            <div
                                className={`rounded-full w-full p-2 pt-2.5 ${
                                    step === "payment"
                                        ? "bg-regal-blue text-white"
                                        : ""
                                }`}
                            >
                                Thanh toán
                            </div>
                            <div
                                className={`rounded-full w-full p-2 pt-2.5 ${
                                    step === "receipt"
                                        ? "bg-regal-blue text-white"
                                        : ""
                                }`}
                            >
                                Biên nhận
                            </div>
                        </div>
                    </div>
                    {step === "cart" && (
                        <Cart setStep={(step) => setStep(step)} />
                    )}
                    {step === "delivery" && (
                        <Delivery
                            setStep={(step) => setStep(step)}
                            firstRender={firstRender}
                        />
                    )}
                    {step === "payment" && (
                        <Payment setStep={(step) => setStep(step)} />
                    )}
                    {step === "receipt" && <Receipt />}
                </div>
            </div>
        </>
    );
};
