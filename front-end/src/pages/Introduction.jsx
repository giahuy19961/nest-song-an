import background from "../assets/images/background-image.jpg";
export const Introduction = () => {
    return (
        <>
            <div
                className="pt-40 bg-fixed relative bg-cover h-[32rem] bg-no-repeat bg-center text-right text-white"
                style={{
                    backgroundImage: `url(${background})`,
                }}
            >
                <h1 className="mr-44 font-cur text-7xl text-[#295F2D]">
                    Yến sào Song Ân
                </h1>

                <p className="mr-44 font-sans pl-[49rem] text-black font-[600] text-[17px]">
                    Nỗ lực hết mình vì sứ mệnh đem đến sản phẩm Yến Sào chất
                    lượng nhất cho người Việt
                </p>
            </div>
            <div className="text-center">
                <div>
                    <div className="text-3xl font-bold mt-16">
                        Đặc điểm, lợi ích của yến sào
                    </div>
                    <div className="border p-[0.7px] mt-1.5 w-4/12 m-auto border-cyan-700" />
                    <div className="p-10 px-36 font-sans">
                        <p>
                            Sử dụng yến sào thường xuyên giúp bồi bổ cơ thể,
                            chống suy nhược, giúp ăn ngon miệng, ngủ sâu giấc,
                            giải tỏa căng thẳng, tăng cường trí nhớ. Đặc biệt
                            giúp phục hồi chức năng phổi, tốt cho tim mạch, gan,
                            thận sau khi nhiễm bệnh.
                        </p>
                        <p className="mt-5">
                            Theo nghiên cứu của Trung tâm Công nghệ Sinh học Đại
                            học Thủy Sản và Viện Công nghệ Sinh học, trong thành
                            phần của yến sào chứa 18 mẫu acid amin trong đấy có
                            một số loại có hàm lượng cao như Aspartic acid,
                            Serine, Tyrosine, Valine, Leucine,… Hàm lượng acid
                            Syalic chiếm 8,6% và Tyrosine có tác dụng làm phục
                            hồi các tổn thương lúc bị nhiễm độc, kích thích phát
                            triển hồng cầu. Rất nhiều kết quả gần đây cho rằng,
                            việc dùng tổ yến với cơ thể nhiễm độc còn khiến hạn
                            chế mức độ sút cân, ổn định một số chỉ tiêu về huyết
                            học cũng như giúp cơ thể phục hồi một cách mau
                            chóng.
                        </p>
                    </div>
                </div>
                <div>
                    <div className="text-3xl font-bold mt-16">
                        Quy trình sản xuất
                    </div>
                    <div className="border p-[0.7px] mt-1.5 w-4/12 m-auto border-cyan-700" />
                    <p className="p-10 px-36 font-sans">
                        Với kinh nghiệm làm việc lâu năm trong ngành Yến Sào,
                        chúng tôi tự hào tạo ra 100% Yến Sào tinh khiết, không
                        dùng chất tẩy rửa, không trộn phụ gia, sử dụng công nghệ
                        vô trùng bằng tia cực tím (UV) để khử khuẩn, giúp giữ
                        trọn thành phần dinh dưỡng và màu sắc của tổ Yến.
                    </p>
                </div>
                <div>
                    <div className="text-3xl font-bold mt-16">
                        Giá trị cốt lõi
                    </div>
                    <div className="border p-[0.7px] mt-1.5 w-4/12 m-auto border-cyan-700" />
                    <div className="p-10 px-36 flex flex-col gap-5 font-sans">
                        <p>
                            Chất lượng: Tổ yến sào Song Ân có nguồn gốc từ yến
                            nuôi và yến đảo Nha Trang 100% nguyên chất. Chúng
                            tôi cam kết các sản phẩm đều được làm sạch và chưng
                            theo phương pháp thủ công, không chất tạo mùi, bảo
                            quản.
                        </p>
                        <p>
                            Tâm huyết: Song Ân đảm bảo thu hoạch yến nhân đạo,
                            đảm bảo môi trường sống cho yến, phát triển mở rộng
                            khu vực nuôi yến.
                        </p>
                        <p>
                            Khách hàng là số 1: Thân thiện và nồng ấm trong giao
                            tiếp với khách hàng. Lắng nghe tâm tư, nguyện vọng
                            của khách để thỏa mãn tối đa nhu cầu về yến
                        </p>
                        <p>
                            Phát triển bền vững: Song Ân cam kết tạo ra giá trị
                            bền vững, đặt trọng tâm và lợi ích lâu dài cho quý
                            khách hàng và đối tác.
                        </p>
                    </div>
                </div>
            </div>
        </>
    );
};
