import ReactSlider from "react-slider";
import { CheckBox } from "../CheckBox/CheckBox";
import "./stylesAccordion.css";
const FILTER_CARD = ["50g", "100g"];
export const Accordion = ({ handleCheck, handleRange }) => {
    return (
        <div className="accordion" id="accordionExample5">
            <div className="accordion-item bg-white border-b border-gray-200">
                <h2 className="accordion-header mb-0" id="headingOne5">
                    <button
                        className=" accordion-button relative flex items-center w-full py-4 px-5 text-base text-gray-800 text-left bg-white border-0 rounded-none transition focus:outline-none"
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#collapseOne5"
                        aria-expanded="true"
                        aria-controls="collapseOne5"
                    >
                        <div className="mr-3 font-semibold text-zinc-500">
                            &#43;
                        </div>
                        Khối lượng
                    </button>
                </h2>
                <div
                    id="collapseOne5"
                    className="accordion-collapse collapse show"
                    aria-labelledby="headingOne5"
                >
                    <div className="accordion-body py-3 px-5">
                        {FILTER_CARD.map((item, index) => (
                            <CheckBox
                                value={item}
                                handleCheck={handleCheck}
                                key={index}
                            />
                        ))}
                    </div>
                </div>
            </div>
            <div className="accordion-item bg-white border-b border-gray-200">
                <h2 className="accordion-header mb-0" id="headingTwo5">
                    <button
                        className=" accordion-button collapsed relative flex items-center w-full py-4 px-5 text-base text-gray-800 text-left bg-white border-0 rounded-none transition focus:outline-none "
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#collapseTwo5"
                        aria-expanded="false"
                        aria-controls="collapseTwo5"
                    >
                        <div className="mr-3 font-semibold text-zinc-500">
                            &#43;
                        </div>
                        Giá
                    </button>
                </h2>
                <div
                    id="collapseTwo5"
                    className="accordion-collapse collapse"
                    aria-labelledby="headingTwo5"
                >
                    <div className="accordion-body pb-16 pt-4 mx-2 text-xs">
                        <div className="mb-4 ml-2 font-semibold">
                            Đơn vị: Nghìn VNĐ
                        </div>
                        <ReactSlider
                            className="horizontal-slider"
                            thumbClassName="thumb font-semibold  text-orange-600 rounded-lg cursor-pointer "
                            trackClassName="example-track"
                            defaultValue={[0, 50000]}
                            ariaLabel={["Lower thumb", "Upper thumb"]}
                            ariaValuetext={(state) =>
                                `Thumb value ${state.valueNow}`
                            }
                            renderThumb={(props, state) => (
                                <div {...props}>
                                    <div className="bg-regal-blue px-1 py-2.5 rounded-lg"></div>
                                    <div className={`thumb-${state.index}`}>
                                        {state.valueNow}
                                    </div>
                                </div>
                            )}
                            pearling
                            onChange={(value) => handleRange(value)}
                            minDistance={10}
                            max={50000}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};
