export const Pagination = ({ page, products, setPage }) => {
    return (
        <div className="flex gap-2 justify-center">
            {/* Arrow previous button */}
            {page > 1 ? (
                <div
                    className="border py-1 px-3 rounded-full flex items-center  hover:bg-regal-blue/25  hover:text-white cursor-pointer"
                    onClick={() => setPage(page - 1)}
                >
                    <svg
                        aria-hidden="true"
                        className="w-3 h-3"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                            clipRule="evenodd"
                        ></path>
                    </svg>
                </div>
            ) : (
                // Hidden button to keep form of pagination
                <div className="py-1 px-3 invisible">
                    <svg
                        aria-hidden="true"
                        className="w-3 h-3"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                            clipRule="evenodd"
                        ></path>
                    </svg>
                </div>
            )}

            {/* Number previous button */}
            {page > 1 ? (
                <div
                    className="border py-1 px-3 rounded-full hover:bg-regal-blue/25  hover:text-white cursor-pointer"
                    onClick={() => setPage(page - 1)}
                >
                    {page - 1}
                </div>
            ) : (
                // Hidden button to keep form of pagination
                <div className="py-1 px-3 invisible">{page - 1}</div>
            )}

            {/* Current page */}
            <div className="border py-1 px-3 rounded-full bg-regal-blue/60 text-white">
                {page}
            </div>

            {/* Number next button */}
            {page * 9 < products.length ? (
                <div
                    className="border py-1 px-3 rounded-full  hover:bg-regal-blue/25  hover:text-white cursor-pointer"
                    onClick={() => setPage(page + 1)}
                >
                    {page + 1}
                </div>
            ) : (
                // Hidden button to keep form of pagination
                <div className="py-1 px-3 invisible">{page + 1}</div>
            )}

            {/* Arrow next button */}
            {page * 9 < products.length ? (
                <div
                    className="border py-1 px-3 rounded-full flex items-center  hover:bg-regal-blue/25  hover:text-white cursor-pointer"
                    onClick={() => setPage(page + 1)}
                >
                    <svg
                        aria-hidden="true"
                        className="w-3 h-3"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                            clipRule="evenodd"
                        ></path>
                    </svg>
                </div>
            ) : (
                // Hidden button to keep form of pagination
                <div className="py-1 px-3 invisible">
                    <svg
                        aria-hidden="true"
                        className="w-3 h-3"
                        fill="currentColor"
                        viewBox="0 0 20 20"
                        xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                            fillRule="evenodd"
                            d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                            clipRule="evenodd"
                        ></path>
                    </svg>
                </div>
            )}
        </div>
    );
};
