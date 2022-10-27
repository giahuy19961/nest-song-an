import { Link, useLocation } from "react-router-dom";
export const HeaderBottom = () => {
    const active = useLocation().pathname.slice(1, 100);
    return (
        <nav className="flex md:justify-center items-center justify-around text-sm text-white">
            <Link
                to="/"
                className={`transition-colors flex items-center duration-300 h-full px-3 ${
                    active === "" ? "bg-regal-blue/60" : ""
                }`}
                onClick={() => window.scrollTo(0, 0)}
            >
                Trang chủ
            </Link>
            <Link
                to="/introduction"
                className={`transition-colors flex items-center duration-300 h-full px-3 ${
                    active === "introduction" ? "bg-regal-blue/60" : ""
                }`}
                onClick={() => window.scrollTo(0, 0)}
            >
                Giới thiệu
            </Link>
            <Link
                to="/production"
                className={`transition-colors flex items-center duration-300 h-full px-3 ${
                    active === "production" ? "bg-regal-blue/60" : ""
                }`}
                onClick={() => window.scrollTo(0, 0)}
            >
                Sản phẩm
            </Link>
            <Link
                to="/new"
                className={`transition-colors flex items-center duration-300 h-full px-3 ${
                    active === "new" ? "bg-regal-blue/60" : ""
                }`}
                onClick={() => window.scrollTo(0, 0)}
            >
                Tin tức
            </Link>
            <Link
                to="/ddd"
                className={`transition-colors flex items-center duration-300 h-full px-3 ${
                    active === "ddd" ? "bg-regal-blue/60" : ""
                }`}
                onClick={() => window.scrollTo(0, 0)}
            >
                Liên hệ
            </Link>
        </nav>
    );
};
