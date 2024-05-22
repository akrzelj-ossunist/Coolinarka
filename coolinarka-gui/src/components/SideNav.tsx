import { useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useLocation } from "react-router-dom";
import { logout } from "../redux/features/authentication/authenticate";
import { AppDispacth, RootState } from "../redux/store";
import { useOnClickOutside } from "usehooks-ts";

const SideNav: React.FC<{ sx?: string; navContent?: any[] }> = ({
  sx,
  navContent,
}) => {
  const dispatch = useDispatch<AppDispacth>();
  const authenticate = useSelector((state: RootState) => state.authenticate);

  const [dropMenu, setDropMenu] = useState(false);

  const ref = useRef<HTMLDivElement | null>(null);
  useOnClickOutside(ref, () => setDropMenu(false));

  const location = useLocation();
  const path = location.pathname;

  return (
    <div
      className={`${sx} h-[100vh] bg-white shadow-2xl z-10 flex pl-11 py-11 flex-col overflow-y-auto`}>
      {navContent?.map((content, index) => {
        return (
          <div key={index} className="flex items-center">
            {content.title === "Logo" ? (
              <p className="mb-20 flex justify-center ml-[-1vw]">
                {content.logo}
              </p>
            ) : content.title === "Settings" ? (
              <div
                onClick={() => setDropMenu(dropMenu ? false : true)}
                className={`flex items-center my-4 w-full cursor-pointer relative`}>
                {content.logo}
                <p className={`font-bold text-xl m-2 mt-3 text-[#bbbbbb]`}>
                  {content.title}
                </p>
                {dropMenu && (
                  <div
                    ref={ref}
                    className="absolute bg-white shadow-2xl shadow-[#40514e] rounded-lg flex flex-col right-6 top-[75%]">
                    {authenticate.success ? (
                      <p
                        onClick={() => dispatch(logout())}
                        className="cursor-pointer hover:bg-[#40514e] px-10 py-2 rounded-md font-semibold hover:text-white ease-in-out duration-300">
                        Logout
                      </p>
                    ) : (
                      <>
                        <Link
                          to={"/Login"}
                          className="cursor-pointer hover:bg-[#40514e] px-10 py-2 rounded-t-md font-semibold hover:text-white ease-in-out duration-300">
                          Login
                        </Link>
                        <Link
                          to={"/Register"}
                          className="cursor-pointer hover:bg-[#40514e] px-10 py-2 rounded-b-md font-semibold hover:text-white ease-in-out duration-300">
                          Register
                        </Link>
                      </>
                    )}
                  </div>
                )}
              </div>
            ) : (
              <Link
                to={`/${content.title}`}
                className={`flex items-center my-4 w-full cursor-pointer ease-in-out duration-300 ${
                  path === "/" + content.title && "border-r-4 border-[#40514e]"
                }`}>
                {content.logo}
                <p
                  className={`font-bold ${
                    path === "/" + content.title
                      ? "text-[#40514e]"
                      : "text-[#bbbbbb]"
                  } text-xl m-2 mt-3 ease-in-out duration-300`}>
                  {content.title}
                </p>
              </Link>
            )}
          </div>
        );
      })}
    </div>
  );
};

export default SideNav;
