import * as React from "react";
import { styled, useTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import MuiDrawer from "@mui/material/Drawer";
import MuiAppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import List from "@mui/material/List";
import CssBaseline from "@mui/material/CssBaseline";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";
import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import InboxIcon from "@mui/icons-material/MoveToInbox";
import MailIcon from "@mui/icons-material/Mail";
import {
    Button,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    TextField,
} from "@mui/material";
import PermIdentityOutlinedIcon from "@mui/icons-material/PermIdentityOutlined";
import { useState, useMemo, useEffect } from "react";
import AddIcon from "@mui/icons-material/Add";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import { useForm, Controller } from "react-hook-form";
import { productApi } from "../../api/productApi";
import { ToastPageChange } from "../Toast";
import { Outlet } from "react-router-dom";

const drawerWidth = 240;

const openedMixin = (theme) => ({
    width: drawerWidth,
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
    }),
    overflowX: "hidden",
});

const closedMixin = (theme) => ({
    transition: theme.transitions.create("width", {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    overflowX: "hidden",
    width: `calc(${theme.spacing(7)} + 1px)`,
    [theme.breakpoints.up("sm")]: {
        width: `calc(${theme.spacing(8)} + 1px)`,
    },
});

const DrawerHeader = styled("div")(({ theme }) => ({
    display: "flex",
    alignItems: "center",
    justifyContent: "flex-end",
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
}));

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
    zIndex: theme.zIndex.drawer + 1,
    backgroundColor: theme.palette.secondary.main,
    transition: theme.transitions.create(["width", "margin"], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(["width", "margin"], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

const Drawer = styled(MuiDrawer, {
    shouldForwardProp: (prop) => prop !== "open",
})(({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: "nowrap",
    boxSizing: "border-box",
    ...(open && {
        ...openedMixin(theme),
        "& .MuiDrawer-paper": openedMixin(theme),
    }),
    ...(!open && {
        ...closedMixin(theme),
        "& .MuiDrawer-paper": closedMixin(theme),
    }),
}));

export default function AdminLayout({ children, setRerender }) {
    const theme = useTheme();
    const [cateList, setCateList] = useState([]);
    const [open, setOpen] = useState(false);
    const [title, setTitle] = useState("User");

    useEffect(() => {
        const fetchCategory = async () => {
            try {
                const response = await productApi.getCategory();
                setCateList(response);
            } catch (error) {
                console.log(error);
            }
        };
        fetchCategory();
    }, []);

    const inputArray = useMemo(() => {
        return [
            {
                name: "image",
                label: "Hình ảnh",
                type: "text",
                values: "",
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "name",
                label: "Tên",
                values: "",
                type: "text",
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "description",
                label: "Mô tả",
                values: "",
                type: "text",
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "basePrice",
                label: "Giá",
                values: "",
                type: "number",
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "quantity",
                label: "Số lượng",
                type: "number",
                values: "",
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "cateId",
                label: "Loại",
                type: "select",
                values: cateList,
                rules: { required: "Truong nay la bat buoc" },
            },
            {
                name: "deal",
                label: "Khuyến mãi",
                type: "number",
                values: "",
                rules: {
                    required: "Truong nay la bat buoc",
                    validate: (value) => {
                        const dealRule = +value >= 1 || +value < 0;
                        if (dealRule) {
                            return "Khuyến mãi phải < 1 và >= 0!";
                        }
                    },
                },
            },
        ];
    }, [cateList]);
    const {
        control,
        handleSubmit,
        watch,
        formState: { errors },
        reset,
    } = useForm();

    const onSubmit = (data) => {
        const formData = {
            ...data,
            quantity: +data.quantity,
            cateId: +data.cateId,
            basePrice: +data.basePrice,
            deal: +data.deal,
        };
        productApi
            .addProductAPI(formData)
            .then((res) => {
                setRerender(true);
            })
            .then(() => {
                handleClose();
            })
            .catch((err) => console.log(err));
    };

    const handleDrawerOpen = () => {
        setOpen(true);
    };

    const handleDrawerClose = () => {
        setOpen(false);
    };

    // Handle open form dialog

    const [formOpen, setFormOpen] = useState(false);

    const handleClickOpen = () => {
        setFormOpen(true);
    };

    const handleClose = () => {
        reset();
        setFormOpen(false);
    };

    //------------------------

    return (
        <Box
            sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}
        >
            <CssBaseline />
            <AppBar position="fixed" open={open}>
                <Toolbar>
                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                    >
                        <MenuIcon />
                    </IconButton>
                    <Box
                        sx={{
                            display: "flex",
                            justifyContent: "space-between",
                            alignItems: "center",
                            width: "100%",
                        }}
                    >
                        <Typography variant="h6" noWrap component="div">
                            {title}
                        </Typography>
                        <Box sx={{ display: "flex" }}>
                            <Button
                                variant="contained"
                                onClick={handleClickOpen}
                            >
                                <AddIcon />
                                Thêm sản phẩm
                            </Button>
                            <Box
                                sx={{
                                    width: "50px",
                                    height: "50px",
                                    borderRadius: "50%",
                                    border: "1px solid #ccc",
                                    display: "flex",
                                    justifyContent: "center",
                                    alignItems: "center",
                                    marginLeft: "40px",
                                }}
                            >
                                <PermIdentityOutlinedIcon />
                            </Box>
                        </Box>
                    </Box>
                </Toolbar>
            </AppBar>
            <Drawer variant="permanent" open={open}>
                <DrawerHeader>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === "rtl" ? (
                            <ChevronRightIcon />
                        ) : (
                            <ChevronLeftIcon />
                        )}
                    </IconButton>
                </DrawerHeader>
                <Divider />
                <List>
                    {["User", "Product", "Order"].map((text, index) => (
                        <ListItem
                            key={text}
                            disablePadding
                            sx={{ display: "block" }}
                        >
                            <ListItemButton
                                onClick={() => {
                                    setTitle(text);
                                }}
                                sx={{
                                    minHeight: 48,
                                    justifyContent: open ? "initial" : "center",
                                    px: 2.5,
                                }}
                            >
                                <ListItemIcon
                                    sx={{
                                        minWidth: 0,
                                        mr: open ? 3 : "auto",
                                        justifyContent: "center",
                                    }}
                                >
                                    {index % 2 === 0 ? (
                                        <InboxIcon />
                                    ) : (
                                        <MailIcon />
                                    )}
                                </ListItemIcon>
                                <ListItemText
                                    primary={text}
                                    sx={{ opacity: open ? 1 : 0 }}
                                />
                            </ListItemButton>
                        </ListItem>
                    ))}
                </List>
            </Drawer>
            <Box
                component="main"
                sx={{ flexGrow: 1, p: 3, backgroundColor: "#d9eeef" }}
                minHeight="100vh"
            >
                <DrawerHeader />
                <Grid container direction={'column'} spacing={"24px"}>
                    <Outlet/>
                </Grid>
            
                
            </Box>

            <Dialog open={formOpen} onClose={handleClose}>
                <DialogTitle
                    color={"#538ffd"}
                    marginTop={"20px"}
                    sx={{ paddingX: "44px" }}
                >
                    Thêm Mới Sản Phẩm
                </DialogTitle>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <DialogContent sx={{ paddingX: "44px" }}>
                        {inputArray.map(
                            ({ label, name, rules, type, values }, index) => (
                                <Controller
                                    key={index}
                                    name={name}
                                    control={control}
                                    rules={rules}
                                    render={({ field }) => {
                                        return type != "select" ? (
                                            <TextField
                                                {...field}
                                                margin="dense"
                                                label={label}
                                                fullWidth
                                                variant="standard"
                                                type={type}
                                                error={Boolean(errors[name])}
                                                helperText={
                                                    errors[name]?.message
                                                }
                                            />
                                        ) : (
                                            <FormControl
                                                fullWidth
                                                variant="standard"
                                            >
                                                <InputLabel id="demo-simple-select-label">
                                                    {label}
                                                </InputLabel>
                                                <Select
                                                    {...field}
                                                    labelId="demo-simple-select-label"
                                                    id="demo-simple-select"
                                                    label={label}
                                                    error={Boolean(
                                                        errors[name]
                                                    )}
                                                    helperText={
                                                        errors[name]?.message
                                                    }
                                                >
                                                    {values.map((category) => (
                                                        <MenuItem
                                                            value={category.id}
                                                        >
                                                            {category.name}
                                                        </MenuItem>
                                                    ))}
                                                </Select>
                                            </FormControl>
                                        );
                                    }}
                                />
                            )
                        )}
                    </DialogContent>
                    <DialogActions
                        sx={{ paddingTop: "20px", paddingBottom: "32px" }}
                    >
                        <Button onClick={handleClose} sx={{ width: "100px" }}>
                            Cancel
                        </Button>
                        <Button
                            type="submit"
                            sx={{
                                width: "140px",
                                height: "40px",
                                borderRadius: "500px",
                            }}
                            variant={"contained"}
                        >
                            Thêm Mới
                        </Button>
                    </DialogActions>
                </form>
            </Dialog>
            {/* <ToastPageChange url={"/"} name={"Bán hàng"} /> */}
        </Box>
    );
}
