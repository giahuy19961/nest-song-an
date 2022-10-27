import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { newApi } from "../../api/newApi";
import { Loading } from "../../components/Loading/Loading";
import {
    NEWS_LOADING_ALL_SUCCESS,
    NEWS_LOADING_FAIL,
    NEWS_LOADING_REQUEST,
    selectNew,
} from "../../features/new/newSlice";
import { getErrorMessageFromServer } from "../../utils/serverUtils";

export const New = () => {
    const [page, setPage] = useState(1);
    const news = useSelector(selectNew);
    const dispatch = useDispatch();

    useEffect(() => {
        const fetchNews = async () => {
            try {
                dispatch(NEWS_LOADING_REQUEST());
                const response = await newApi.getAllNew();
                //localStorage.setItem("news", response);
                dispatch(NEWS_LOADING_ALL_SUCCESS(response));
            } catch (error) {
                const errorMessage = getErrorMessageFromServer(error);
                dispatch(NEWS_LOADING_FAIL(errorMessage));
            }
        };
        fetchNews();
    }, []);

    return (
        <>
            {news.loading ? (
                <Loading />
            ) : (
                <div className="pt-16">
                    <div className="p-16 px-32">
                        <h1 className="font-semibold md:text-3xl text-md md:mb-16 mb-5">
                            Tin tức
                        </h1>
                        <div>
                            {news.news.map((card) => (
                                <Link
                                    to={`/new/${card.id}`}
                                    key={card.id}
                                    onClick={() => window.scrollTo(0, 0)}
                                >
                                    <div className="flex my-5 gap-4">
                                        <div className="rounded-sm">
                                            <img
                                                src={card.listImages[0].imgPath}
                                                alt={card.title}
                                                className="aspect-square object-cover w-52 rounded"
                                            />
                                        </div>
                                        <div className="p-6 pt-0 px-10 w-8/12">
                                            <h1 className="text-3xl font-bold mb-5 uppercase">
                                                {card.title}
                                            </h1>
                                            <p className="text-gray-500/80">
                                                {card.shortDescription}...
                                            </p>
                                        </div>
                                    </div>
                                    <div className="border-t-2 w-full border-gray-200" />
                                </Link>
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};
