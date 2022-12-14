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
import { Outlet, useNavigate } from "react-router-dom";
import { NAVIGATIONS } from "../../constants";

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

  const navigate = useNavigate();

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
        label: "H??nh ???nh",
        type: "text",
        values: "",
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "name",
        label: "T??n",
        values: "",
        type: "text",
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "description",
        label: "M?? t???",
        values: "",
        type: "text",
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "basePrice",
        label: "Gi??",
        values: "",
        type: "number",
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "quantity",
        label: "S??? l?????ng",
        type: "number",
        values: "",
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "cateId",
        label: "Lo???i",
        type: "select",
        values: cateList,
        rules: { required: "Truong nay la bat buoc" },
      },
      {
        name: "deal",
        label: "Khuy???n m??i",
        type: "number",
        values: "",
        rules: {
          required: "Truong nay la bat buoc",
          validate: (value) => {
            const dealRule = +value >= 1 || +value < 0;
            if (dealRule) {
              return "Khuy???n m??i ph???i < 1 v?? >= 0!";
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
              <Button variant="contained" onClick={handleClickOpen}>
                <AddIcon />
                Th??m s???n ph???m
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
          {NAVIGATIONS.map((nav, index) => (
            <ListItem key={index} disablePadding sx={{ display: "block" }}>
              <ListItemButton
                onClick={() => {
                  setTitle(nav.title);
                  navigate(nav.url);
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
                  {nav.icon}
                </ListItemIcon>
                <ListItemText
                  primary={nav.title}
                  sx={{ opacity: open ? 1 : 0 }}
                />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
      </Drawer>
      <Box
        component="main"
        sx={{ flexGrow: 1, backgroundColor: "#d9eeef" }}
        minHeight="100vh"
      >
        <DrawerHeader />
        <Grid
          container
          direction={"column"}
          spacing={"24px"}
          padding="24px"
          paddingLeft="48px"
          paddingTop={"48px"}
        >
          <Outlet />
        </Grid>
      </Box>

      <Dialog open={formOpen} onClose={handleClose}>
        <DialogTitle
          color={"#538ffd"}
          marginTop={"20px"}
          sx={{ paddingX: "44px" }}
        >
          Th??m M???i S???n Ph???m
        </DialogTitle>
        <form onSubmit={handleSubmit(onSubmit)}>
          <DialogContent sx={{ paddingX: "44px" }}>
            {inputArray.map(({ label, name, rules, type, values }, index) => (
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
                      helperText={errors[name]?.message}
                    />
                  ) : (
                    <FormControl fullWidth variant="standard">
                      <InputLabel id="demo-simple-select-label">
                        {label}
                      </InputLabel>
                      <Select
                        {...field}
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        label={label}
                        error={Boolean(errors[name])}
                        helperText={errors[name]?.message}
                      >
                        {values.map((category) => (
                          <MenuItem value={category.id}>
                            {category.name}
                          </MenuItem>
                        ))}
                      </Select>
                    </FormControl>
                  );
                }}
              />
            ))}
          </DialogContent>
          <DialogActions sx={{ paddingTop: "20px", paddingBottom: "32px" }}>
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
              Th??m M???i
            </Button>
          </DialogActions>
        </form>
      </Dialog>
      {/* <ToastPageChange url={"/"} name={"B??n h??ng"} /> */}
    </Box>
  );
}
