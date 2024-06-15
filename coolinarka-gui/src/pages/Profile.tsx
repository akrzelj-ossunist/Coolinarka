import { useEffect, useState } from "react";
import RecipeList from "../components/RecipeList";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import RecipeFilters from "../components/RecipeFilters";
import { Link, useNavigate } from "react-router-dom";
import useGetUserById from "../services/getUserById";

const Profile: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const navigate = useNavigate();

  useEffect(() => {
    !authenticateState.success && navigate("/Login");
  }, [authenticateState.success]);

  const { data, isLoading } = useGetUserById(authenticateState.user.id);

  const [filters, setFilters] = useState({
    name: "",
    country: "",
    season: "",
    difficulty: "",
    ingredients: "",
    userId: authenticateState.user.id,
  });

  return isLoading ? (
    <p>Loading...</p>
  ) : (
    <div className="flex flex-wrap w-full">
      <div className="w-full sm:w-[370px] bg-white mr-0 sm:mr-16 border rounded shadow flex flex-col items-center p-10 mb-4 sm:mb-0">
        <div className="flex justify-center bg-[#11999e] w-[100px] h-[100px] rounded-full">
          <div className="rounded-full flex justify-center items-center font-extrabold text-white text-[50px]">
            {data.firstName.charAt(0)}
            {data.lastName.charAt(0)}
          </div>
        </div>
        <p className="font-bold text-[#40514e] mt-4 text-2xl">
          {data.firstName} {data.lastName}
        </p>
        <p className="text-slate-400">{data.username}</p>
        <p className="border rounded shadow mt-6 p-2">{data.bio}</p>
        <p className="mt-6 font-semibold text-[#40514e]">
          Birthday: {data.birthday?.toString().substring(10, 0)}
        </p>
        <div className="mt-6 flex justify-center flex-wrap">
          <Link to={"/User/Edit"}>
            <button className="text-[#40514e] cursor-pointer font-bold w-[170px] text-xl bg-white px-2 h-[38px] my-2 border rounded shadow active:bg-slate-200 mr-3 ml-3">
              Edit Profile
            </button>
          </Link>
          <Link to={"/User/Change-Password"}>
            <button className="text-[#40514e] cursor-pointer font-bold w-[200px] text-xl bg-white px-2 h-[38px] my-2 border rounded shadow mr-3 ml-3 active:bg-slate-200">
              Change Password
            </button>
          </Link>
          <button className="text-white cursor-pointer font-bold w-[170px] rounded-[4px] text-xl bg-red-600 active:bg-red-300 px-2 h-[38px] my-2 mr-3 ml-3">
            Delete
          </button>
        </div>
      </div>
      <div className="w-full">
        <RecipeFilters setFilters={setFilters} />
        <p className="font-semibold text-2xl border-b-2 w-full pb-4 mb-6">
          My Recipes
        </p>
        <RecipeList filters={filters} />
      </div>
    </div>
  );
};

export default Profile;
