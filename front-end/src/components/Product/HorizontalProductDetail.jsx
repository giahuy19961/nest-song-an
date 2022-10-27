import * as React from "react";
import { useState } from "react";
import { useTheme } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import { Button, Grid, TextField } from "@mui/material";
import { productApi } from "../../api/productApi";
import { useNavigate } from "react-router-dom";

export const HorizontalProductDetail = ({
  id,
  image,
  name,
  price,
  deal,
  desc,
  status,
  rerender,
  setRerender,
  successToastStatus,
  setSuccessToastStatus,
}) => {
  const theme = useTheme();
  const [proPrice, setProPrice] = useState(price);
  const [proName, setProName] = useState(name);
  const [proDesc, setProDesc] = useState(desc);

  const handleRerender = () => {
    setSuccessToastStatus(true);
    setRerender(true);
  };

  const handleNameChange = (e) => {
    setProName(e.target.value);
  };

  const handlePriceChange = (e) => {
    setProPrice(e.target.value);
  };

  const handleDescChange = (e) => {
    setProDesc(e.target.value);
  };

  const handleUpdate = () => {
    productApi
      .updateProductById(id, proName, proDesc, proPrice)
      .then((res) => {
        handleRerender();
      })
      .catch((err) => console.log(err));
  };

  const handleDelete = () => {
    productApi
      .deleteProductById(id)
      .then((res) => {
        handleRerender();
      })
      .catch((err) => console.log(err));
  };

  const handleReActive = () => {
    productApi
      .updateProductStatus(id, 1)
      .then(() => {
        handleRerender();
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <Grid item xs={12}>
      <Card
        sx={{
          display: "flex",
          paddingRight: "20px",
          boxShadow: 3,
          // paddingY: "20px",
        }}
      >
        <Grid item xs={1.5}>
          <CardMedia
            component="img"
            sx={{ width: 166, height: 166 }}
            image={image}
            alt="Live from space album cover"
          />
        </Grid>
        <Grid
          item
          xs={3}
          display="flex"
          justifyContent="space-between"
          alignItems="center"
        >
          <CardContent sx={{ flex: "1 0 auto" }}>
            <TextField
              id="outlined-basic"
              label="Name"
              variant="outlined"
              required
              fullWidth
              value={proName}
              onChange={handleNameChange}
            ></TextField>
          </CardContent>
        </Grid>

        <Grid item xs={4.5}>
          <CardContent sx={{ flex: "1 0 auto" }}>
            <TextField
              id="outlined-multiline-static"
              label="Description"
              variant="outlined"
              multiline
              fullWidth
              sx={{ minHeight: "126px" }}
              maxRows={4}
              value={proDesc}
              onChange={handleDescChange}
            ></TextField>
          </CardContent>
        </Grid>

        <Grid item xs={1} display="flex" alignItems="center">
          <TextField
            id="outlined-basic"
            label="Price"
            variant="outlined"
            required
            value={proPrice}
            onChange={handlePriceChange}
          ></TextField>
        </Grid>
        <Grid
          item
          xs={2}
          display="flex"
          justifyContent="space-around"
          alignItems="center"
          paddingX={"16px"}
        >
          <Button variant="contained" color="success" onClick={handleUpdate}>
            Cập nhật
          </Button>
          {status == 1 ? (
            <Button
              variant="contained"
              color="error"
              sx={{ width: "100px" }}
              onClick={handleDelete}
            >
              Xóa
            </Button>
          ) : (
            <Button
              variant="contained"
              sx={{ width: "100px" }}
              onClick={handleReActive}
            >
              Thêm
            </Button>
          )}
        </Grid>
      </Card>
    </Grid>
  );
};
