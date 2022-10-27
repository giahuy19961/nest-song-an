import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { SignIn } from "./pages/SignIn";
import { SignUp } from "./pages/SignUp";
import { Introduction } from "./pages/Introduction";
import { Production } from "./pages/Production/Production";
import { UserProfile } from "./pages/UserProfile";
import { New } from "./pages/New/New";
import Header from "./components/Header/Header";
import { ShoppingCart } from "./pages/ShoppingCart/ShoppingCart";
import { ProductDetail } from "./pages/Production/ProductDetail";
import { NewDetail } from "./pages/New/NewDetail";
import NotFound from "./pages/NotFound";
import { useSelector } from "react-redux";
import { selectUser } from "./features/user/userSlice";
import { Loading } from "./components/Loading/Loading";
import { DashBoard } from "./pages/Admin/DashBoard";
import { ThemeProvider } from "@mui/material";
import { theme } from "./assets/theme";
import { Order } from "./pages/Order/Order";
import { ToastPageChange } from "./components/Toast";
import { useState } from "react";

export const App = () => {
    const [name, setName] = useState("Bán hàng");
    const [url, setUrl] = useState("/");
    const user = useSelector(selectUser);
    const handleSwitch = () => {
        if (url === "/") {
            setName("Quản trị", setUrl("/dashboard"));
        } else {
            setName("Bán hàng", setUrl("/"));
        }
    };
    return (
        <ThemeProvider theme={theme}>
            <BrowserRouter>
                <Header />
                {user.loading ? (
                    <Loading />
                ) : (
                    <Routes>
                        <Route path="/dashboard" element={<DashBoard />} />
                        <Route path="/" element={<Home />} />
                        <Route path="/sign-in" element={<SignIn />} />
                        <Route path="/sign-up" element={<SignUp />} />
                        <Route
                            path="/introduction"
                            element={<Introduction />}
                        />
                        <Route path="/production" element={<Production />} />
                        <Route path="/user" element={<UserProfile />} />
                        <Route path="/new" element={<New />} />
                        <Route
                            path="/shopping-cart"
                            element={<ShoppingCart />}
                        />
                        <Route
                            path="/production/:id"
                            element={<ProductDetail />}
                        />
                        <Route path="/new/:id" element={<NewDetail />} />
                        <Route path="/order" element={<Order />} />
                        <Route path="*" element={<NotFound />} />
                    </Routes>
                )}
                {user.userInfor?.role === "ADMIN" && (
                    <ToastPageChange
                        url={url}
                        name={name}
                        onClick={() => handleSwitch()}
                    />
                )}
            </BrowserRouter>
        </ThemeProvider>
    );
};
