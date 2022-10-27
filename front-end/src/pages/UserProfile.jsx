import { useState } from "react";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { userApi } from "../api/userApi";
import Avatar from "../assets/images/avatar.jpg";
import { Input } from "../components/Input/Input";
import { selectUser } from "../features/user/userSlice";

export const UserProfile = () => {
    const [userInfor, setUserInfor] = useState({});
    const user = useSelector(selectUser);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchInfor = async () => {
            try {
                const response = await userApi.getUserInfor(user.userInfor.id);
                console.log(response);
                setUserInfor(response);
            } catch (error) {
                if (error.response) console.log(error.response.data);
            }
        };
        fetchInfor();
    }, [user]);

    useEffect(() => {
        if (!user.token) navigate("/sign-in");
    }, []);

    return (
        <div className="bg-gray-100 w-full h-screen p-32 pb-20 flex justify-center">
            <div className="shadow-lg w-9/12 h-full bg-white flex">
                <div className="w-4/12 border-r-2 h-full border-gray-200">
                    <div className="p-16 pt-24 pb-3">
                        <img
                            src={Avatar}
                            className="rounded-full border w-28 h-28 m-auto flex justify-center items-center object-cover"
                        />
                    </div>
                    <div className="text-center font-medium text-black/70">
                        {userInfor.fullname}
                    </div>

                    <div className="px-12 mt-8">
                        <div className="py-2">
                            <div className="flex items-center gap-2">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    strokeWidth={1.5}
                                    stroke="currentColor"
                                    className="w-4 h-4"
                                >
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 002.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 01-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 00-1.091-.852H4.5A2.25 2.25 0 002.25 4.5v2.25z"
                                    />
                                </svg>
                                <h1 className="text-sm font-semibold">
                                    Số điện thoại
                                </h1>
                                <p className="text-xs font-medium opacity-60">
                                    (+84) {userInfor.phoneNumber}
                                </p>
                            </div>
                        </div>
                        <div className="py-2">
                            <div className="flex items-center gap-2">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    strokeWidth={1.5}
                                    stroke="currentColor"
                                    className="w-4 h-4"
                                >
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75"
                                    />
                                </svg>
                                <h1 className="text-sm font-semibold">Email</h1>
                                <p className="text-xs font-medium opacity-60">
                                    synhse160311@gmail.com
                                </p>
                            </div>
                        </div>
                        <div className="py-2">
                            <div className="flex items-center gap-2">
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    strokeWidth={1.5}
                                    stroke="currentColor"
                                    className="w-4 h-4"
                                >
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
                                    />
                                </svg>
                                <h1 className="text-sm font-semibold">
                                    Địa chỉ
                                </h1>
                                <p className="text-xs font-medium opacity-60">
                                    {userInfor.address}
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="w-8/12 grid grid-rows-5">
                    <div className="border-b-2 border-gray-200 px-20 pt-12 row-span-3">
                        <h1 className="opacity-60 font-semibold text-center text-lg">
                            Thay đổi thông tin
                        </h1>
                        <div className="mt-6 grid grid-cols-2 gap-4">
                            <div className="text-gray-600 mt-1">
                                <Input type="text" label="Họ và tên" />
                            </div>
                            <div className="text-gray-600 mt-1">
                                <Input
                                    type="number"
                                    label="Số điện thoại"
                                    pattern="[0-9]{10}"
                                />
                            </div>
                            <div className="text-gray-600 mt-1">
                                <Input
                                    type="date"
                                    //label="Ngày sinh"
                                />
                            </div>
                            <div className="text-gray-600 mt-1">
                                <Input type="text" label="Địa chỉ" />
                            </div>
                            <button className="w-fit mt-1 bg-[#00ADB5] text-white py-2 px-8 rounded-full text-sm m-auto col-span-2">
                                Lưu
                            </button>
                        </div>
                    </div>

                    <div className="p-10 pt-6 row-span-2">
                        <h1 className="opacity-60 font-semibold">
                            Thông tin liên hệ
                        </h1>
                        <div className="flex gap-3 justify-center flex-row mt-5">
                            <div className="w-4/12 py-1">
                                <div className="flex items-center gap-2">
                                    <h1 className="text-sm font-semibold">
                                        Phone
                                    </h1>
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        strokeWidth={1.5}
                                        stroke="currentColor"
                                        className="w-4 h-4"
                                    >
                                        <path
                                            strokeLinecap="round"
                                            strokeLinejoin="round"
                                            d="M2.25 6.75c0 8.284 6.716 15 15 15h2.25a2.25 2.25 0 002.25-2.25v-1.372c0-.516-.351-.966-.852-1.091l-4.423-1.106c-.44-.11-.902.055-1.173.417l-.97 1.293c-.282.376-.769.542-1.21.38a12.035 12.035 0 01-7.143-7.143c-.162-.441.004-.928.38-1.21l1.293-.97c.363-.271.527-.734.417-1.173L6.963 3.102a1.125 1.125 0 00-1.091-.852H4.5A2.25 2.25 0 002.25 4.5v2.25z"
                                        />
                                    </svg>
                                </div>
                                <p className="text-xs font-medium opacity-60">
                                    (+84) 0365444461
                                </p>
                            </div>
                            <div className="w-4/12 py-1">
                                <div className="flex items-center gap-2">
                                    <h1 className="text-sm font-semibold">
                                        Email
                                    </h1>
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        strokeWidth={1.5}
                                        stroke="currentColor"
                                        className="w-4 h-4"
                                    >
                                        <path
                                            strokeLinecap="round"
                                            strokeLinejoin="round"
                                            d="M21.75 6.75v10.5a2.25 2.25 0 01-2.25 2.25h-15a2.25 2.25 0 01-2.25-2.25V6.75m19.5 0A2.25 2.25 0 0019.5 4.5h-15a2.25 2.25 0 00-2.25 2.25m19.5 0v.243a2.25 2.25 0 01-1.07 1.916l-7.5 4.615a2.25 2.25 0 01-2.36 0L3.32 8.91a2.25 2.25 0 01-1.07-1.916V6.75"
                                        />
                                    </svg>
                                </div>
                                <p className="text-xs font-medium opacity-60">
                                    synhse160311@gmail.com
                                </p>
                            </div>
                            <div className="w-4/12 py-1">
                                <div className="flex items-center gap-2">
                                    <h1 className="text-sm font-semibold">
                                        Address
                                    </h1>
                                    <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        fill="none"
                                        viewBox="0 0 24 24"
                                        strokeWidth={1.5}
                                        stroke="currentColor"
                                        className="w-4 h-4"
                                    >
                                        <path
                                            strokeLinecap="round"
                                            strokeLinejoin="round"
                                            d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"
                                        />
                                    </svg>
                                </div>
                                <p className="text-xs font-medium opacity-60">
                                    Tòa S302, Vinhome Grand Park, Thành phố Thủ
                                    Đức
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
