import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
    FILTER_ADD_NAME,
    FILTER_ADD_RANGE_PRICE,
    FILTER_REMOVE_NAME,
    selectFilter,
} from "../../features/production/filterSlice";
import { Accordion } from "./Accordion";

export const Filter = () => {
    const filter = useSelector(selectFilter);
    const dispatch = useDispatch();
    const filterTimeoutRef = useRef(null);

    const handleCheck = (value) => {
        const isChecked = filter.name.includes(value);
        isChecked
            ? dispatch(FILTER_REMOVE_NAME(value))
            : dispatch(FILTER_ADD_NAME(value));
    };

    const handleRange = (value) => {
        if (filterTimeoutRef.current) clearTimeout(filterTimeoutRef.current);

        filterTimeoutRef.current = setTimeout(() => {
            dispatch(FILTER_ADD_RANGE_PRICE(value));
        }, 500);
    };

    return (
        <div>
            <div className="md:mt-14 mt-6 ml-10">
                <div className="m-1 flex flex-col md:text-base text-xs">
                    <Accordion
                        handleCheck={handleCheck}
                        handleRange={handleRange}
                    />
                </div>
            </div>
        </div>
    );
};
