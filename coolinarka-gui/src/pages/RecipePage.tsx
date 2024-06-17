import { Link, useParams } from "react-router-dom";
import useGetRecipeByIdQuery from "../services/getRecipeById";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import Reviews from "../components/Reviews";

const RecipePage: React.FC = () => {
  const { recipeId } = useParams();
  const imageUploadsUrl = import.meta.env.VITE_IMAGE_UPLOADS;
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const { data, isLoading } = useGetRecipeByIdQuery(recipeId!);
  return isLoading ? (
    <p>Loading...</p>
  ) : (
    <div>
      <div className="flex w-full mid:flex-wrap mid:items-center mid:justify-center">
        <div className="flex flex-col w-[375px] px-10 pt-4 border rounded shadow overflow-y-auto">
          <img
            src={imageUploadsUrl + data.image}
            alt="Example"
            className="w-[300px] h-[300px]"
          />
          <p className="my-2 font-bold text-2xl text-[#40514e] border-b-2 pb-2">
            {data.name}
          </p>
          <p className="text-xl font-semibold text-[#40514e] mb-3">
            Ingredients:
          </p>
          {data.ingredients.map((ingredient: any) => (
            <div key={ingredient.id} className="flex ml-4">
              <p className="-mt-[2px] mr-1 font-2xl font-extrabold">.</p>
              <p className="my-1 text-sm font-semibold text-[#40514e] mr-2">
                {ingredient.ingredient.substring(0, 1).toUpperCase() +
                  ingredient.ingredient.substring(1)}
                :
              </p>
              <p className="my-1 text-sm font-semibold text-[#11999e] ">
                {ingredient.amount}
                {ingredient.unit}
              </p>
            </div>
          ))}
          <div className="flex mt-2">
            <p className="my-1 font-semibold text-[#40514e] mr-2">Country: </p>
            <p className="my-1 font-semibold text-[#11999e] ">{data.country}</p>
          </div>
          <div className="flex">
            <p className="my-1 font-semibold text-[#40514e] mr-2">
              Prep time:{" "}
            </p>
            <p className="my-1 font-semibold text-[#11999e] ">
              {data.prepTime}mins
            </p>
          </div>
          <div className="flex">
            <p className="my-1 font-semibold text-[#40514e] mr-2">People: </p>
            <p className="my-1 font-semibold text-[#11999e] ">{data.people}</p>
          </div>
          <div className="flex">
            <p className="my-1 font-semibold text-[#40514e] mr-2">Season: </p>
            <p className="my-1 font-semibold text-[#11999e] ">{data.season}</p>
          </div>
        </div>
        <div className="flex flex-col w-[70%] px-10 pt-4 border rounded shadow overflow-y-auto min-w-[400px] ml-5 mid:mt-5 phone:w-[375px] relative">
          {data.user.id === authenticateState.user.id && (
            <Link
              to={`/Recipe/Delete/${data.id}`}
              className="text-white cursor-pointer font-bold w-[170px] rounded-[4px] text-xl bg-red-600 active:bg-red-300 px-2 h-[38px] my-2 mr-3 ml-3 absolute right-2 top-0 flex justify-center items-centery">
              Delete
            </Link>
          )}
          <p className="text-3xl font-semibold text-[#40514e] mb-3 border-b-2 pb-2">
            Description:{" "}
          </p>
          <p className="text-[#11999e] font-semibold">{data.description}</p>{" "}
          <p className="mt-4 text-3xl font-semibold text-[#40514e] mb-3 border-b-2 pb-2">
            Events:{" "}
          </p>
          <div className="flex">
            {data.events.map((event: any, index: any) => (
              <p className="text-[#11999e] font-semibold mx-3" key={index}>
                {event}
              </p>
            ))}
          </div>
          <p className="mt-4 text-3xl font-semibold text-[#40514e] mb-3 border-b-2 pb-2">
            Meal Groups:{" "}
          </p>
          <div className="flex">
            {data.mealGroups.map((group: any, index: any) => (
              <p className="text-[#11999e] font-semibold mx-3" key={index}>
                {group}
              </p>
            ))}
          </div>
          <p className="mt-4 text-3xl font-semibold text-[#40514e] mb-3 border-b-2 pb-2">
            Phases:{" "}
          </p>
          <div className="">
            {data.phases.map((phase: any, index: any) => (
              <div key={index} className="flex font-semibold mb-4">
                <p>{index + 1}.</p>
                <p className="text-[#11999e] font-semibold mx-3">
                  {phase.description}
                </p>
              </div>
            ))}
          </div>
        </div>
      </div>
      <Reviews recipeId={recipeId || ""} />
    </div>
  );
};

export default RecipePage;
