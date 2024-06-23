import { useLocation, useNavigate, useRoutes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import { useEffect } from "react";
import CreateRecipe from "./pages/CreateRecipe";
import EditProfile from "./pages/EditProfile";
import ChangePassword from "./pages/ChangePassword";
import RecipePage from "./pages/RecipePage";
import Favorites from "./pages/Favorites";
import DeleteRecipe from "./pages/DeleteRecipe";
import DeleteUser from "./pages/DeleteUser";
import UserList from "./pages/UserList";
import UpdateRecipe from "./pages/UpdateRecipe";

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
      path: "/User/Edit",
      element: <EditProfile />,
    },
    {
      path: "/User/Change-Password",
      element: <ChangePassword />,
    },
    {
      path: "/Create-recipe",
      element: <CreateRecipe />,
    },
    {
      path: "/Recipe/Delete/:recipeId",
      element: <DeleteRecipe />,
    },
    {
      path: "/Recipe/Update/:recipeId",
      element: <UpdateRecipe />,
    },
    {
      path: "/User/Delete/:userId",
      element: <DeleteUser />,
    },
    {
      path: "/User/List",
      element: <UserList />,
    },
    {
      path: "/Recipe/:recipeId",
      element: <RecipePage />,
    },
    {
      path: "/Favorites",
      element: <Favorites />,
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
