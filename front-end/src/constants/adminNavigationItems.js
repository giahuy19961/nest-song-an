import Person2OutlinedIcon from "@mui/icons-material/Person2Outlined";
import DashboardOutlinedIcon from "@mui/icons-material/DashboardOutlined";
import PaidOutlinedIcon from "@mui/icons-material/PaidOutlined";
import RedeemOutlinedIcon from "@mui/icons-material/RedeemOutlined";
export const NAVIGATIONS = [
  {
    title: "Dashboard",
    url: "/dashboard",
    icon: <DashboardOutlinedIcon />,
  },
  {
    title: "User",
    url: "/dashboard/user",
    icon: <Person2OutlinedIcon />,
  },
  {
    title: "Product",
    url: "/dashboard/product",
    icon: <RedeemOutlinedIcon />,
  },

  {
    title: "Order",
    url: "/dashboard/order",
    icon: <PaidOutlinedIcon />,
  },
];
