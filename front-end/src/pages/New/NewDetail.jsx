import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate, useParams } from "react-router-dom";
import { newApi } from "../../api/newApi";
import { Loading } from "../../components/Loading/Loading";
import {
    NEWS_LOADING_FAIL,
    NEWS_LOADING_ONE_SUCCESS,
    NEWS_LOADING_REQUEST,
    selectNew,
} from "../../features/new/newSlice";
import { getErrorMessageFromServer } from "../../utils/serverUtils";

export const NewDetail = () => {
    const dispatch = useDispatch();
    // const [productImage, setProductImage] = useState();
    const news = useSelector(selectNew);
    const newId = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchNew = async () => {
            try {
                dispatch(NEWS_LOADING_REQUEST());
                const response = await newApi.getNewById(newId.id);
                //localStorage.setItem("news", response);
                dispatch(NEWS_LOADING_ONE_SUCCESS(response));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(NEWS_LOADING_FAIL(errorMessage));
            }
        };
        fetchNew();
    }, [newId.id]);

    let newDes = [];
    if (news.new.description) newDes = news.new.description.split("\n");

    return (
        <>
            {news.loading ? (
                <Loading />
            ) : news.productErrorMessage === 204 ? (
                navigate("*")
            ) : (
                <>
                    <div className="pt-52 bg-gray-100"></div>
                    <div className="pt-10 px-64">
                        <h1 className="text-4xl uppercase font-bold">
                            {news.new.title}
                        </h1>
                        <div className="text-gray-400 font-serif mt-3 flex justify-between">
                            <div>Ngày: {newDes[1]}</div>
                            <div>{newDes[2]}</div>
                        </div>

                        <div className="border-t-2 w-full border-gray-200 my-10" />
                        <div className="flex">
                            <div className="w-8/12 indent-10 pr-8 pt-2">
                                {newDes.map(
                                    (para, index) =>
                                        index > 2 && (
                                            <div className="my-5">{para}</div>
                                        )
                                )}
                            </div>
                            <div className="w-4/12">
                                {news.news.map((card, index) => (
                                    <div
                                        key={index}
                                        className="max-h-64 m-4 text-left"
                                    >
                                        <div className="flex">
                                            <Link
                                                to={`/new/${card.id}`}
                                                className="rounded-sm w-5/12"
                                                onClick={() =>
                                                    window.scrollTo(0, 0)
                                                }
                                            >
                                                <img
                                                    src={
                                                        card.listImages[0]
                                                            .imgPath
                                                    }
                                                    alt={card.title}
                                                    className="aspect-square object-cover"
                                                />
                                            </Link>
                                            <div className="text-xs w-7/12">
                                                <Link
                                                    to={`/new/${card.id}`}
                                                    onClick={() =>
                                                        window.scrollTo(0, 0)
                                                    }
                                                >
                                                    <h1
                                                        className="text-truncate text-base p-2 font-semibold leading-6 uppercase"
                                                        title={card.title}
                                                    >
                                                        {card.title}
                                                    </h1>
                                                </Link>
                                                <p className="text-truncate h-auto px-2 text-gray-400/70">
                                                    {card.shortDescription}
                                                </p>
                                                <div className="w-full text-right p-2 text-[#00ADB5]">
                                                    <Link
                                                        to={`/new/${card.id}`}
                                                        onClick={() =>
                                                            window.scrollTo(
                                                                0,
                                                                0
                                                            )
                                                        }
                                                    >
                                                        Xem thêm &#187;
                                                    </Link>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>

                        <div className="border-t-2 w-full border-gray-200 my-10" />
                    </div>
                </>
            )}
        </>
    );
};
