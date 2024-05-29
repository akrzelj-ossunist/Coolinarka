import HamMenu from "./HamMenu";
import HamMenuIcon from "../assets/HamMenuIcon";
import { useState } from "react";

const Navbar: React.FC<{ sx?: string; navContent?: any[] }> = ({
  sx,
  navContent,
}) => {
  const [showMenu, setShowMenu] = useState(false);

  const handleMenuToggle = () => {
    setShowMenu((prevState) => !prevState);
  };

  return (
    <div
      className={`${sx} bg-red shadow-2xl flex fixed top-0 left-0 z-50 w-full`}>
      <div className="flex justify-between items-center px-10 py-2 w-full phone:px-7 relative">
        {navContent?.map((content, index) => {
          return (
            content.title === "Logo" && (
              <p key={index} className=" flex justify-center">
                {content.logo}
              </p>
            )
          );
        })}
        <HamMenuIcon
          className="w-9 h-9 cursor-pointer phone:w-8"
          onClick={handleMenuToggle}
        />
        <HamMenu
          navContent={navContent}
          showMenu={showMenu}
          setShowMenu={setShowMenu}
        />
      </div>
    </div>
  );
};

export default Navbar;
