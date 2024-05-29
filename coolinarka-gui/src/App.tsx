import { useLocation, useNavigate, useRoutes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import { useEffect } from "react";
import CreateRecipe from "./pages/CreateRecipe";

function App() {
  const navigate = useNavigate();
  const location = useLocation();

  const routes = useRoutes([
    {
      path: "/Home",
      element: <Home />,
    },
    {
      path: "/Login",
      element: <Login />,
    },
    {
      path: "/Register",
      element: <Register />,
    },
    {
      path: "/Profile",
      element: <Profile />,
    },
    {
      path: "/Create-recipe",
      element: <CreateRecipe />,
    },
  ]);

  useEffect(() => {
    if (location.pathname === "/") {
      navigate("/Home");
    }
  }, [location, navigate]);

  return <div className="flex w-full h-full">{routes}</div>;
}

export default App;
