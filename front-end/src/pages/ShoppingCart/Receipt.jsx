import { Link } from "react-router-dom";

export const Receipt = () => {
    return (
        <div className="mt-10 w-9/12 m-auto pb-32">
            <div className="text-center text-4xl font-semibold mb-10 text-regal-blue">
                Cảm ơn bạn đã đặt hàng^^
            </div>
            <div className="text-center px-32">
                Đơn hàng của bạn sẽ được giao trong vòng 30 ngày. Mọi chi tiết,
                thắc mắc của khách hàng có thể liên hệ với chúng tôi thông qua
                phần liên hệ bên dưới cùng của website{" "}
                <span className="underline font-medium text-cyan-400 cursor-pointer">
                    Liên hệ
                </span>
            </div>
            <div className="text-center mt-5">
                Theo dõi tiến trình đơn hàng thông qua phần{" "}
                <Link to="/order" onClick={() => window.scrollTo(0, 0)}>
                    <span className="underline font-medium text-cyan-400 cursor-pointer">
                        Đơn mua
                    </span>
                </Link>
            </div>
        </div>
    );
};
