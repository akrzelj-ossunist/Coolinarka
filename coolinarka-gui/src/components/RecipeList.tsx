import { Link } from "react-router-dom";
import useGetRecipesQuery from "../services/getRecipes";
import Favorite from "./Favorite";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import Pagination from "./Pagination";
import { useQueryClient } from "@tanstack/react-query";

const RecipeList: React.FC<{ filters: any }> = ({ filters }) => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const imageUploadsUrl = import.meta.env.VITE_IMAGE_UPLOADS;
  const searchParams = new URLSearchParams(location.search);
  const currentPage = parseInt(searchParams.get("page") ?? "1");
  const { data, isLoading } = useGetRecipesQuery({
    ...filters,
    page: currentPage,
  });
  const queryClient = useQueryClient();
  !isLoading && console.log(data);
  return (
    <>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <div className="grid gap-4 grid-cols-4">
          {data.recipePage.map((recipe: any) => (
            <div
              key={recipe.id}
              className="p-4 border rounded shadow w-80 relative mb-10">
              <Link to={`/recipe/${recipe.id}`} className="w-full">
                <img
                  src={imageUploadsUrl + recipe.image}
                  alt="Example"
                  className="w-full"
                />
                <div>
                  <p className="font-bold py-2">{recipe.name}</p>
                  <p className="border-[1px] p-3">
                    {recipe.description.substring(0, 100) + "..."}
                  </p>
                </div>
              </Link>
              {authenticateState.success && <Favorite recipeId={recipe.id} />}
            </div>
          ))}
        </div>
      )}
      {!isLoading && (
        <Pagination
          currentPage={currentPage}
          lastPage={data.lastPage}
          onClick={() =>
            queryClient.invalidateQueries({
              queryKey: ["recipeList"],
            })
          }
        />
      )}
    </>
  );
};

export default RecipeList;
