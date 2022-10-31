import { Box, Grid, Paper } from "@mui/material";
import { useEffect, useMemo, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import AppTable from "../../../components/Table";
import AddOutlinedIcon from "@mui/icons-material/AddOutlined";

import { AppButton } from "../../../components/Element/Button";
import { Loading } from "../../../components/Loading/Loading";
import { useDispatch, useSelector } from "react-redux";
import { getUsers } from "../../../app/reducer/usersSlice";

export function Users() {
  const [page, setPage] = useState(0);
  const [pageSize, setPageSize] = useState(10);

  const dispatch = useDispatch();
  const { data, loading, error } = useSelector((state) => state.users);

  const navigate = useNavigate();

  const columns = useMemo(() => {
    return [
      { field: "username", headerName: "User Name", width: 300 },
      {
        field: "fullname",
        headerName: "Full Name",
        width: 250,
      },
      { field: "dateOfBirth", headerName: "Day of birth", width: 350 },
      {
        field: "address",
        headerName: "Address",
        width: 200,
      },

      {
        field: "role",
        headerName: "Permission",
        width: 150,
        renderCell: ({ value }) => {
          return <span>{value?.name} </span>;
        },
      },
      {
        field: "status",
        headerName: "Status",
        width: 150,
        renderCell: ({ value }) => {
          return <span>{value === 1 ? "Đang hoạt động" : "Đã khóa"} </span>;
        },
      },
      {
        field: "id",
        headerName: "Action",
        width: 150,
        renderCell: ({ value }) => {
          return (
            <AppButton
              onClick={(e) => {
                e.stopPropagation();
                console.log(value);
              }}
              to={`/dasboard/user/edit/${value}`}
              style={{ textTransform: "capitalize" }}
            >
              Edit
            </AppButton>
          );
        },
      },
    ];
  }, []);

  useEffect(() => {
    dispatch(getUsers({ offset: page + 1, limit: pageSize }));
  }, []);

  if (loading) return <Loading />;

  if (error) return <Navigate to="404" />;

  return (
    <>
      <Grid item variant="outlined" style={{ display: "flex" }}>
        {/* For search & Filter */}
        <Paper
          sx={{
            width: "100%",
            padding: "12px",
            display: "flex",
            justifyContent: "space-between",
            paddingInline: "24px",
            alignItems: "center",
          }}
        >
          <Box>Table Helper</Box>
          <Box>
            <AppButton
              variant="contained"
              startIcon={<AddOutlinedIcon />}
              onClick={() => {
                navigate("/dashboard/user/create");
              }}
              style={{ textTransform: "capitalize" }}
            >
              Create
            </AppButton>
          </Box>
        </Paper>
      </Grid>
      <Grid
        item
        sx={{ width: "100%" }}
        variant="outlined"
        style={{ display: "flex" }}
      >
        <Paper sx={{ width: "100%", padding: "24px", height: "100%" }}>
          <AppTable
            data={data?.users}
            columns={columns}
            page={page}
            pageSize={pageSize}
            setPage={setPage}
            setPageSize={setPageSize}
            autoHeight
            total={data?.total}
          />
        </Paper>
      </Grid>
    </>
  );
}
