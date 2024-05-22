import { useLocation } from "react-router-dom";
import FavoritesIcon from "../assets/FavoritesIcon";
import HomeIcon from "../assets/Home";
import LogoSvg from "../assets/Logo";
import SettingsIcon from "../assets/SettingsIcon";
import UserIcon from "../assets/UserIcon";
import SideNav from "./SideNav";
import Navbar from "./Navbar";

const Layout: React.FC<{ children: JSX.Element }> = ({ children }) => {
  const location = useLocation();
  const path = location.pathname;

  const mainSideNav = [
    {
      title: "Logo",
      logo: <LogoSvg className="w-64 phone:w-48" />,
    },
    {
      title: "Home",
      logo: (
        <HomeIcon
          className="w-8 h-8 ease-in-out duration-300"
          fill={path === "/Home" ? "#11999e" : "#bbbbbb"}
        />
      ),
    },
    {
      title: "Favorites",
      logo: (
        <FavoritesIcon
          className="w-8 h-8 ease-in-out duration-300"
          stroke={path === "/Favorites" ? "#11999e" : "#bbbbbb"}
        />
      ),
    },
    {
      title: "Profile",
      logo: (
        <UserIcon
          className="w-8 h-8 ease-in-out duration-300"
          fill={path === "/Profile" ? "#11999e" : "#bbbbbb"}
        />
      ),
    },
    {
      title: "Settings",
      logo: <SettingsIcon className="w-9 h-9" />,
    },
  ];
  return (
    <div className="flex h-screen">
      <SideNav
        sx={"rounded-r-2xl w-[310px] tablet:hidden"}
        navContent={mainSideNav}
      />
      <Navbar sx={"desktop:hidden"} navContent={mainSideNav} />
      <div className="pl-16 py-14 pr-10 overflow-y-auto tablet:w-full tablet:px-4">
        {children}
      </div>
      <SideNav sx={"rounded-l-2xl hidden fixed top-0 right-0 w-[310px]"} />
    </div>
  );
};

export default Layout;
