import { logout } from "../redux/features/authentication/authenticate";
import { Link } from "react-router-dom";
import LoginIcon from "../assets/LoginIcon";
import LogoutIcon from "../assets/LogoutIcon";
import { useLocation } from "react-router-dom";
import { AppDispacth, RootState } from "../redux/store";
import { useDispatch, useSelector } from "react-redux";
import { useRef } from "react";
import { useOnClickOutside } from "usehooks-ts";

const HamMenu: React.FC<{
  navContent?: any[];
  showMenu: boolean;
  setShowMenu: (val: boolean) => void;
}> = ({ navContent, showMenu, setShowMenu }) => {
  const dispatch = useDispatch<AppDispacth>();
  const authenticate = useSelector((state: RootState) => state.authenticate);

  const location = useLocation();
  const path = location.pathname;

  const ref = useRef<HTMLDivElement | null>(null);
  useOnClickOutside(ref, () => setShowMenu(false));

  return (
    <div
      ref={ref}
      className={`absolute right-0 top-16 bg-white w-[300px] phone:w-full shadow-xl ${
        showMenu ? "translate-x-0" : "translate-x-full"
      } ease-in-out duration-300`}>
      {navContent?.map((content, index) => {
        return (
          content.title !== "Logo" &&
          (content.title === "Settings" ? (
            authenticate.success ? (
              <div
                className="flex items-center my-4 w-full cursor-pointer hover:bg-slate-200 ease-in-out duration-300 px-6 py-2"
                onClick={() => {
                  setShowMenu(false);
                  dispatch(logout());
                }}>
                <LogoutIcon className="w-8 h-8" />
                <p className="font-bold text-[#bbbbbb] text-xl m-2 mt-3">
                  Logout
                </p>
              </div>
            ) : (
              <Link
                to={`/Login`}
                onClick={() => setShowMenu(false)}
                className={`flex items-center w-full cursor-pointer ease-in-out duration-300 hover:bg-slate-200 px-6 py-2 ${
                  path === "/Login" && "border-r-4 border-[#40514e]"
                }`}>
                <p key={index} className=" flex justify-center">
                  <LoginIcon
                    className="w-8 h-8 ease-in-out duration-300"
                    fill={path === "/Login" ? "#11999e" : "#bbbbbb"}
                  />
                </p>
                <p
                  className={`font-bold ${
                    path === "/Login" ? "text-[#40514e]" : "text-[#bbbbbb]"
                  } text-xl m-2 mt-3 ease-in-out duration-300`}>
                  Login
                </p>
              </Link>
            )
          ) : (
            <Link
              onClick={() => setShowMenu(false)}
              to={`/${content.title}`}
              className={`flex items-center w-full cursor-pointer ease-in-out duration-300 hover:bg-slate-200 px-6 py-2 ${
                path === "/" + content.title && "border-r-4 border-[#40514e]"
              }`}>
              <p key={index} className=" flex justify-center">
                {content.logo}
              </p>
              <p
                className={`font-bold ${
                  path === "/" + content.title
                    ? "text-[#40514e]"
                    : "text-[#bbbbbb]"
                } text-xl m-2 mt-3 ease-in-out duration-300`}>
                {content.title}
              </p>
            </Link>
          ))
        );
      })}
    </div>
  );
};

export default HamMenu;
