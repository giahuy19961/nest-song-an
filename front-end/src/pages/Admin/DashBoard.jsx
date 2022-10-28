import { useEffect, useState } from "react";
import AdminLayout from "../../components/layout/AdminLayout";
import { HorizontalProductDetail } from "../../components/Product/HorizontalProductDetail";
import { productApi } from "../../api/productApi";
import { Grid, Pagination } from "@mui/material";
import { ToastSuccess } from "../../components/Toast";

export const DashBoard = () => {
  const [isRerender, setIsRerender] = useState(false);
  const [product, setProduct] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageCount, setPageCount] = useState(1);
  const [successToastStatus, setSuccessToastStatus] = useState(false);
  useEffect(() => {
    productApi
      .getCountAllProduct()
      .then((res) => setPageCount(res))
      .catch((err) => {
        console.log(err);
      });

    return () => {};
  }, []);

  useEffect(() => {
    productApi
      .getAllProductByPage(currentPage, 8)
      .then((res) => setProduct(res))
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setIsRerender(false);
      });
  }, [currentPage, isRerender]);

  const handleChangePage = (e) => {
    setCurrentPage(e.target.textContent);
  };

  return (
    // <AdminLayout setRerender={setIsRerender}>
    <>
      <Grid container spacing={3}>
        {product.map((item) => (
          <HorizontalProductDetail
            key={item.id}
            id={item.id}
            name={item.name}
            image={item.listImages[0]?.imgPath}
            price={item.basePrice}
            deal={item.deal}
            desc={item.description}
            status={item.status}
            rerender={isRerender}
            setRerender={setIsRerender}
            successToastStatus={successToastStatus}
            setSuccessToastStatus={setSuccessToastStatus}
          />
        ))}
      </Grid>
      <Pagination
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          paddingTop: "40px",
          backgroundColor: "#d9eeef",
        }}
        count={Math.ceil(pageCount / 8)}
        color="primary"
        onChange={handleChangePage}
      />

      <ToastSuccess
        successToastStatus={successToastStatus}
        setSuccessToastStatus={setSuccessToastStatus}
      ></ToastSuccess>
    {/* </AdminLayout> */}
    </>
  );
};
