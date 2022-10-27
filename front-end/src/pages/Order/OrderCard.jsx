import * as React from "react";
import { styled } from "@mui/material/styles";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell, { tableCellClasses } from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { convertPriceToString } from "../../utils/serverUtils";
import { Link } from "react-router-dom";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
    [`&.${tableCellClasses.head}`]: {
        backgroundColor: "#00ADB5",
        color: theme.palette.common.white,
    },
    [`&.${tableCellClasses.body}`]: {
        fontSize: 14,
    },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
    "&:nth-of-type(odd)": {
        backgroundColor: theme.palette.action.hover,
    },
    // hide last border
    "&:last-child td, &:last-child th": {
        border: 0,
    },
}));

function createData(id, product, quantity, price) {
    return {
        id,
        product,
        quantity,
        price,
    };
}

export const OrderCard = ({ card }) => {
    const rows = card.listBillDetails.map((data) =>
        createData(data.id, data.product, data.quantity, data.price)
    );

    const status = [
        { color: "", title: "" },
        { color: "", title: "" },
        { color: "text-amber-500", title: "Đang giao hàng" },
        { color: "text-green-400", title: "Đã hoàn thành" },
        { color: "text-red-600", title: "Đã hủy" },
    ];

    const paymentStatus = {
        "Chưa Thanh Toán": "text-red-600",
        "Đã Thanh Toán": "text-sky-600",
    };

    return (
        <div className="my-9 border p-3 rounded-md shadow-lg bg-white">
            <div className="flex justify-between p-5 px-2 text-xl">
                <div className="font-medium text-gray-500">
                    Giao hàng tới {card.address}, SĐT: {card.phoneNumber}
                </div>
                <div className={`${status[card.status].color} font-semibold`}>
                    {status[card.status].title}
                </div>
            </div>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <StyledTableCell
                                sx={{
                                    fontSize: 18,
                                    width: 900,
                                    fontWeight: "550",
                                }}
                            >
                                Sản phẩm
                            </StyledTableCell>
                            <StyledTableCell
                                align="center"
                                sx={{ fontSize: 18, fontWeight: "550" }}
                            >
                                Đơn giá
                            </StyledTableCell>
                            <StyledTableCell
                                align="center"
                                sx={{ fontSize: 18, fontWeight: "550" }}
                            >
                                Số lượng
                            </StyledTableCell>
                            <StyledTableCell
                                align="center"
                                sx={{ fontSize: 18, fontWeight: "550" }}
                            >
                                Thành tiền
                            </StyledTableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {rows.map((row) => (
                            <TableRow
                                key={row.id}
                                sx={{
                                    "&:last-child td, &:last-child th": {
                                        border: 0,
                                    },
                                }}
                            >
                                <TableCell component="th" scope="row">
                                    <div className="grid grid-cols-12 font-verda">
                                        <Link
                                            to={`/production/${row.product.id}`}
                                            onClick={() =>
                                                window.scrollTo(0, 0)
                                            }
                                        >
                                            <img
                                                src={
                                                    row.product.listImages[0]
                                                        .imgPath
                                                }
                                                className="w-16 h-16 object-cover"
                                            />
                                        </Link>
                                        <div className="col-span-8 ml-3">
                                            <Link
                                                to={`/production/${row.product.id}`}
                                                onClick={() =>
                                                    window.scrollTo(0, 0)
                                                }
                                            >
                                                <div className="text-base uppercase font-bold text-left text-slate-600">
                                                    {row.product.name}
                                                </div>
                                            </Link>
                                            <div className="truncate text-xs text-gray-400">
                                                {row.product.description}
                                            </div>
                                        </div>
                                    </div>
                                </TableCell>
                                <TableCell align="center">
                                    {convertPriceToString(
                                        row.product.basePrice
                                    )}
                                </TableCell>
                                <TableCell align="center">
                                    {row.quantity}
                                </TableCell>
                                <TableCell align="center">
                                    {convertPriceToString(row.price)}
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <div className="text-right px-6 py-4 pb-2 text-lg font-medium">
                Tổng:
                <span
                    className="text-green-600 font-semibold ml-4"
                    style={{
                        textShadow:
                            "0px 1px 2px rgb(198, 245, 218, 0.8), 1px 2px 4px rgb(198, 245, 218, 0.8)",
                    }}
                >
                    {convertPriceToString(card.totalPrice)} VNĐ
                </span>
            </div>
            <div
                className={`${
                    paymentStatus[card.paymentStatusCode]
                } text-right px-6 pb-4 text-xl font-semibold`}
            >
                {card.paymentStatusCode}
            </div>
        </div>
    );
};
