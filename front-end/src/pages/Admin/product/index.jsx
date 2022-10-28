import { Box, Grid, Paper } from "@mui/material";
import { useMemo, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import AppTable from "../../../components/Table";
import AddOutlinedIcon from '@mui/icons-material/AddOutlined';
import { useGetCategories, useGetProductsCount, useGetProductsPagination } from "./api/hooks"
import { AppButton } from "../../../components/Element/Button";
import { Loading } from "../../../components/Loading/Loading";


export function Products() {
    const [page, setPage] = useState(0);
    const [pageSize, setPageSize] = useState(10);
    const { data, error, loading } = useGetProductsPagination({ offset: page + 1, limit: pageSize });
    const { data: categories } = useGetCategories({})
    const { data: count } = useGetProductsCount({});
    const navigate = useNavigate()

    const columns = useMemo(() => {
        return [
            { field: 'name', headerName: 'Name', width: 300, },
            {
                field: 'listImages', headerName: 'Image', width: 150,
                renderCell: (values) => {
                    console.log(values)
                    return <img src={values?.value ? values.value[0].imgPath : ""}></img>
                }


            },
            { field: 'description', headerName: 'Description', width: 350 },
            {
                field: 'cateId', headerName: 'Category', width: 200, renderCell: ({ value }) => {
                    return <span>{(categories || [])?.find(category => category?.id === value)?.name || ''} </span>
                }
            },
            { field: 'basePrice', headerName: 'Price', width: 150 },
            {
                field: 'deal', headerName: 'Deal', width: 150, renderCell: ({ value }) => {
                    return <span>{`${+value * 100} %`} </span>
                }
            },
            {
                field: 'status', headerName: 'Status', width: 150, renderCell: ({ value }) => {
                    return <span>{value === 1 ? "Đang bán" : "Ngưng bán"} </span>
                }
            },
            {
                field: 'id', headerName: 'Action', width: 150, renderCell: ({ value }) => {
                    return <AppButton onClick={(e)=>{
                        e.stopPropagation()
                        console.log(value)
                    }} to={`/product/edit/${value}`} style={{textTransform:"capitalize"}}>Edit</AppButton>
                }
            },
        ]
    }, [categories])

    if (loading) return <Loading/>

    if (error) return <Navigate to='404'/>

    return <>
        <Grid item variant="outlined" style={{ display: "flex" }}>
            {/* For search & Filter */}
            <Paper sx={{ width: "100%", padding: "12px",display:"flex", justifyContent:"space-between",paddingInline:"24px",alignItems:"center" }}>
                <Box>
                    Table Helper
                </Box>
                <Box>
                    <AppButton
                     variant="contained"
                     startIcon={<AddOutlinedIcon/>}
                        onClick={() => {
                            navigate('/product/create')
                        }}
                        style={{textTransform:"capitalize"}}
                        >
                        
                        Create
                    </AppButton>
                </Box>
            </Paper>

        </Grid >
        <Grid item sx={{ width: "100%" }} variant="outlined" style={{ display: "flex" }}>
            <Paper sx={{width:"100%",padding:"24px",height:"100%"}}>
                <AppTable data={data || []} columns={columns} page={page} pageSize={pageSize} setPage={setPage} setPageSize={setPageSize} autoHeight total={count}  />
            </Paper>
        </Grid>
    </>
}