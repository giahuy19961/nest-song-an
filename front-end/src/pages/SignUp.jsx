import { useCallback, useEffect } from "react";
import { useForm } from "react-hook-form";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import { userApi } from "../api/userApi";
import {
    selectUser,
    USER_LOGIN_SUCCESS,
    USER_REQUEST,
} from "../features/user/userSlice";
import { getErrorMessageFromServer } from "../utils/serverUtils";

export const SignUp = () => {
    const user = useSelector(selectUser);
    const { register, handleSubmit } = useForm();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onSubmit = (input) => {
        console.log(input);
        const FetchUser = async () => {
            try {
                dispatch(USER_REQUEST());
                const response = await userApi.signUp(input);
                console.log(response);
                localStorage.setItem("token", JSON.stringify(response));
                dispatch(USER_LOGIN_SUCCESS(JSON.stringify(response)));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                console.log(error);
                // dispatch(USER_LOGIN_FAIL(errorMessage));
            }
        };
        FetchUser();
    };

    useEffect(() => {
        if (user.token) navigate("/");
    }, [user.token]);

    return (
        <section className="h-screen">
            <div
                className="h-full w-full text-gray-800 bg-fixed"
                style={{
                    backgroundImage: "url(bg-image.jpg)",
                    backgroundSize: "100%",
                    backgroundPosition: "100%",
                }}
            >
                <div className="flex flex-col xl:justify-center lg:justify-center justify-center items-center flex-wrap h-full">
                    <div className="md:w-fit w-11/12 border border-gray-300 bg-white flex blocks rounded-lg p-10 pb-7 shadow-2xl shadow-gray-800">
                        <div className="lg:w-80 w-full px-4 md:px-0">
                            <form onSubmit={handleSubmit(onSubmit)}>
                                <div className="text-center font-bold text-xl my-3 ">
                                    ĐĂNG KÝ
                                </div>
                                <div>
                                    <div className="relative inputBox mt-3">
                                        <input
                                            {...register("fullname")}
                                            className="px-3 py-3 border focus:border-regal-blue focus:outline-none w-full text-slate-900 rounded-md"
                                            type="text"
                                            required
                                            maxLength={70}
                                            minLength={1}
                                        />
                                        <span
                                            className={`absolute left-0 mx-2 px-1 my-3 w-fit pointer-events-none transition-all duration-500 rounded-md text-gray-400`}
                                        >
                                            Họ và tên
                                        </span>
                                    </div>
                                    <div className="relative inputBox mt-3">
                                        <input
                                            {...register("username")}
                                            className="px-3 py-3 border focus:border-regal-blue focus:outline-none w-full text-slate-900 rounded-md"
                                            type="text"
                                            required
                                            maxLength={70}
                                            minLength={3}
                                        />
                                        <span
                                            className={`absolute left-0 mx-2 px-1 my-3 w-fit pointer-events-none transition-all duration-500 rounded-md text-gray-400`}
                                        >
                                            Tên đăng nhập
                                        </span>
                                    </div>
                                    <div className="relative inputBox mt-3">
                                        <input
                                            {...register("password")}
                                            className="px-3 py-3 border focus:border-regal-blue focus:outline-none w-full text-slate-900 rounded-md"
                                            type="password"
                                            required
                                            maxLength={70}
                                            minLength={8}
                                        />
                                        <span
                                            className={`absolute left-0 mx-2 px-1 my-3 w-fit pointer-events-none transition-all duration-500 rounded-md text-gray-400`}
                                        >
                                            Mật khẩu
                                        </span>
                                    </div>
                                    <div className="relative inputBox mt-3">
                                        <input
                                            {...register("address")}
                                            className="px-3 py-3 border focus:border-regal-blue focus:outline-none w-full text-slate-900 rounded-md"
                                            type="text"
                                            required
                                            maxLength={70}
                                            minLength={1}
                                        />
                                        <span
                                            className={`absolute left-0 mx-2 px-1 my-3 w-fit pointer-events-none transition-all duration-500 rounded-md text-gray-400`}
                                        >
                                            Địa chỉ
                                        </span>
                                    </div>
                                    <div className="relative inputBox mt-3">
                                        <input
                                            {...register("phoneNumber")}
                                            className="px-3 py-3 border focus:border-regal-blue focus:outline-none w-full text-slate-900 rounded-md"
                                            type="number"
                                            pattern="[0-9]{10}"
                                            required
                                            maxLength={70}
                                            minLength={1}
                                        />
                                        <span
                                            className={`absolute left-0 mx-2 px-1 my-3 w-fit pointer-events-none transition-all duration-500 rounded-md text-gray-400`}
                                        >
                                            Số điện thoại
                                        </span>
                                    </div>
                                </div>
                                <button className="w-full mt-5 inline-block p-2.5 hover:bg-gray-700 text-white font-medium text-xs leading-snug uppercase rounded shadow-gray-400 bg-gray-500 transition duration-100 ease-in-out">
                                    Đăng ký
                                </button>
                                <div className="flex items-center my-4">
                                    <div className="flex-1 border-t border-gray-300 mt-0.5" />
                                </div>

                                <div className="text-sm text-center opacity-60 mt-4">
                                    Bạn đã có tài khoản?&nbsp;
                                    <Link to="/sign-in">
                                        <span className="text-blue-600 font-bold active:text-blue-400">
                                            Đăng nhập
                                        </span>
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};
