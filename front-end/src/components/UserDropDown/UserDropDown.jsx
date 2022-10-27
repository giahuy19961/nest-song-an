import { Slide } from "@mui/material";
import React from "react";
import { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";

import UserIcon from "../../assets/icons/user.png";
import { ORDER_CLEAR } from "../../features/order/orderSlice";
import {
    selectUser,
    USER_LOGOUT_SUCCESS,
    USER_REQUEST,
} from "../../features/user/userSlice";

const UserDropDown = () => {
    const user = useSelector(selectUser);
    const dispatch = useDispatch();
    const [userDropDown, setUserDropDown] = useState("hidden");
    const [logoutSuccess, setLogoutSuccess] = useState(user.isloggedOutSuccess);

    const handleLogout = async () => {
        const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));
        dispatch(USER_REQUEST());
        localStorage.removeItem("token");
        localStorage.removeItem("cart");
        await delay(500);
        dispatch(ORDER_CLEAR());
        dispatch(USER_LOGOUT_SUCCESS());
        setLogoutSuccess(true);
        setTimeout(() => setLogoutSuccess(false), 2000);
    };

    return (
        <>
            <div>
                <Slide
                    direction="down"
                    in={logoutSuccess}
                    mountOnEnter
                    unmountOnExit
                >
                    <div
                        className="z-10 fixed w-fit top-20 right-14 transition-all duration-200 bg-cyan-100 rounded-lg py-5 px-6 mb-3 text-base text-cyan-700 inline-flex items-center"
                        role="alert"
                    >
                        <svg
                            aria-hidden="true"
                            focusable="false"
                            data-prefix="fas"
                            data-icon="info-circle"
                            className="w-4 h-4 mr-2 fill-current"
                            role="img"
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 512 512"
                        >
                            <path
                                fill="currentColor"
                                d="M256 8C119.043 8 8 119.083 8 256c0 136.997 111.043 248 248 248s248-111.003 248-248C504 119.083 392.957 8 256 8zm0 110c23.196 0 42 18.804 42 42s-18.804 42-42 42-42-18.804-42-42 18.804-42 42-42zm56 254c0 6.627-5.373 12-12 12h-88c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h12v-64h-12c-6.627 0-12-5.373-12-12v-24c0-6.627 5.373-12 12-12h64c6.627 0 12 5.373 12 12v100h12c6.627 0 12 5.373 12 12v24z"
                            ></path>
                        </svg>
                        Đăng xuất thành công
                    </div>
                </Slide>
                <div
                    className="relative block text-left hover:cursor-pointer"
                    onMouseOver={() => setUserDropDown("inline")}
                    onMouseOut={() => setUserDropDown("hidden")}
                >
                    <img
                        src={UserIcon}
                        className="border-r px-1 w-10"
                        style={{ filter: "brightness(0) invert(1)" }}
                    />
                    <div
                        className={`${userDropDown} absolute right-0 z-10 w-40 rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none`}
                        role="menu"
                        id="user-drop-down"
                        aria-orientation="vertical"
                        aria-labelledby="menu-button"
                        tabIndex={-1}
                    >
                        <div className="py-1" role="none">
                            <Link
                                to="/user"
                                className="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-200"
                                role="menuitem"
                                tabIndex={-1}
                                id="menu-item-0"
                                onClick={() => window.scrollTo(0, 0)}
                            >
                                Tài Khoản
                            </Link>
                            <Link
                                to="/order"
                                className="text-gray-700 block px-4 py-2 text-sm hover:bg-gray-200"
                                role="menuitem"
                                tabIndex={-1}
                                id="menu-item-1"
                                onClick={() => window.scrollTo(0, 0)}
                            >
                                Đơn Mua
                            </Link>
                            {user.token ? (
                                <div
                                    className="text-gray-700 block w-full px-4 py-2 text-left text-sm hover:bg-gray-200"
                                    role="menuitem"
                                    tabIndex={-1}
                                    id="menu-item-3"
                                    onClick={() => handleLogout()}
                                >
                                    Đăng Xuất
                                </div>
                            ) : (
                                <Link
                                    to="/sign-in"
                                    className="text-gray-700 block w-full px-4 py-2 text-left text-sm hover:bg-gray-200"
                                    role="menuitem"
                                    tabIndex={-1}
                                    id="menu-item-3"
                                >
                                    Đăng Nhập
                                </Link>
                            )}
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default UserDropDown;
