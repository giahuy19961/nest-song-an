import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { List } from "../components/Production/List";
import background from "../assets/images/background-image.jpg";
import { Sale } from "../components/Production/Sale";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBagShopping, faFire } from "@fortawesome/free-solid-svg-icons";
import { useDispatch, useSelector } from "react-redux";
import { selectUser, USER_CLEAR } from "../features/user/userSlice";
import { Slide } from "@mui/material";

export const Home = () => {
    const user = useSelector(selectUser);
    const dispatch = useDispatch();
    const [loginSuccess, setLoginSuccess] = useState(user.isloggedInSuccess);

    useEffect(() => {
        if (user.isloggedInSuccess)
            setTimeout(() => setLoginSuccess(false), 2000);
        dispatch(USER_CLEAR());
    }, []);

    return (
        <>
            <Slide
                direction="down"
                in={loginSuccess}
                mountOnEnter
                unmountOnExit
            >
                <div
                    className="z-10 fixed w-fit top-20 right-14 transition-all duration-200 bg-green-100 rounded-lg py-5 px-6 mb-3 text-base text-green-700 inline-flex items-center"
                    role="alert"
                >
                    <svg
                        aria-hidden="true"
                        focusable="false"
                        data-prefix="fas"
                        data-icon="check-circle"
                        className="w-4 h-4 mr-2 fill-current"
                        role="img"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 512 512"
                    >
                        <path
                            fill="currentColor"
                            d="M504 256c0 136.967-111.033 248-248 248S8 392.967 8 256 119.033 8 256 8s248 111.033 248 248zM227.314 387.314l184-184c6.248-6.248 6.248-16.379 0-22.627l-22.627-22.627c-6.248-6.249-16.379-6.249-22.628 0L216 308.118l-70.059-70.059c-6.248-6.248-16.379-6.248-22.628 0l-22.627 22.627c-6.248 6.248-6.248 16.379 0 22.627l104 104c6.249 6.249 16.379 6.249 22.628.001z"
                        ></path>
                    </svg>
                    ????ng nh???p th??nh c??ng
                </div>
            </Slide>
            <div>
                <div className="pb-10">
                    <div
                        className="pt-40 bg-fixed relative bg-cover h-[32rem] bg-no-repeat bg-center text-right text-white"
                        style={{
                            backgroundImage: `url(${background})`,
                        }}
                    >
                        <h1 className="mr-44 font-cur text-7xl text-[#295F2D]">
                            Y???n s??o Song ??n
                        </h1>

                        <p className="mr-44 font-sans pl-[49rem] text-black font-[600] text-[17px]">
                            N??? l???c h???t m??nh v?? s??? m???nh ??em ?????n s???n ph???m Y???n S??o
                            ch???t l?????ng nh???t cho ng?????i Vi???t
                        </p>
                    </div>
                </div>
                <div>
                    <div className="md:mx-48 md:my-10 mx-4 my-4">
                        <div className="font-medium md:text-3xl text-md md:mb-8 mb-5">
                            <FontAwesomeIcon icon={faFire} />
                            <span className="ml-4">S???n ph???m b??n ch???y</span>
                        </div>
                    </div>
                    <div className="px-48">
                        <Sale />
                    </div>
                </div>
                <div className="md:mx-48 md:my-20 md:mt-28 md:mb-4 mx-4 my-4">
                    <div className="font-medium md:text-3xl text-md md:mb-8 mb-5">
                        <FontAwesomeIcon icon={faBagShopping} />
                        <span className="ml-4">Y???n s??o</span>
                    </div>
                    <List inProductPage={false} />
                </div>
                <Link to="/production" onClick={() => window.scrollTo(0, 0)}>
                    <div className="flex justify-center mb-10">
                        <button className="w-fit bg-[#00ADB5] text-white py-2 px-8 rounded-[3px] text-base">
                            Xem th??m
                        </button>
                    </div>
                </Link>
            </div>
        </>
    );
};
