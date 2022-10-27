import { useEffect } from "react";
import { useState } from "react";
import { productApi } from "../../api/productApi";
import { Filter } from "../../components/Production/Filter";
import { List } from "../../components/Production/List";
import { getErrorMessageFromServer } from "../../utils/serverUtils";

export const Production = () => {
    const [category, setCategory] = useState({ id: 0, name: "Phân loại" });
    const [cateList, setCateList] = useState([]);

    useEffect(() => {
        const fetchCategory = async () => {
            try {
                const response = await productApi.getCategory();
                setCateList(response);
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                setCategory(errorMessage);
            }
        };
        fetchCategory();
    }, []);

    return (
        <div>
            <div className="flex md:pt-16 pt-20 bg-gray-100">
                <div className="md:w-3/12 w-3/12 font-verda pr-12 shadow-md bg-white">
                    <Filter />
                </div>
                <div className="md:w-11/12 w-9/12 md:p-16 md:pl-10 md:ml-4 p-4 shadow-md bg-white">
                    <div>
                        <div className="flex flex-col">
                            <div className="flex mb-4">
                                <div>
                                    <div className="dropdown relative">
                                        <button
                                            className="border dropdown-toggle px-5 py-2 font-medium text-xs leading-tight rounded-full shadow-md transition duration-150 ease-in-out flex items-center whitespace-nowrap"
                                            type="button"
                                            id="dropdownMenuButton1"
                                            data-bs-toggle="dropdown"
                                            aria-expanded="false"
                                        >
                                            {category.name}
                                            <svg
                                                aria-hidden="true"
                                                focusable="false"
                                                data-prefix="fas"
                                                data-icon="caret-down"
                                                className="w-2 ml-2"
                                                role="img"
                                                xmlns="http://www.w3.org/2000/svg"
                                                viewBox="0 0 320 512"
                                            >
                                                <path
                                                    fill="currentColor"
                                                    d="M31.3 192h257.3c17.8 0 26.7 21.5 14.1 34.1L174.1 354.8c-7.8 7.8-20.5 7.8-28.3 0L17.2 226.1C4.6 213.5 13.5 192 31.3 192z"
                                                />
                                            </svg>
                                        </button>
                                        <ul
                                            className=" dropdown-menu min-w-max absolute bg-white text-base z-50 float-left py-2 list-none text-left rounded-lg shadow-lg mt-1 hidden m-0 bg-clip-padding border "
                                            aria-labelledby="dropdownMenuButton1"
                                        >
                                            {cateList.map((cate) => (
                                                <li key={cate.id}>
                                                    <div
                                                        className="cursor-pointer dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent text-gray-700 hover:bg-gray-100 "
                                                        onClick={() =>
                                                            setCategory(cate)
                                                        }
                                                    >
                                                        {cate.name}
                                                    </div>
                                                </li>
                                            ))}
                                            <li key={0}>
                                                <div
                                                    className="cursor-pointer dropdown-item text-sm py-2 px-4 font-normal block w-full whitespace-nowrap bg-transparent text-gray-700 hover:bg-gray-100 "
                                                    onClick={() =>
                                                        setCategory({
                                                            id: 0,
                                                            name: "Phân loại",
                                                        })
                                                    }
                                                >
                                                    Phân loại
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <List inProductPage={true} category={category} />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};
