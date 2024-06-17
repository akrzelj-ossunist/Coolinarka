import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import useGetUserFavQuery from "../services/getUserFavorites";
import { Link } from "react-router-dom";
import Favorite from "../components/Favorite";

const Favorites: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const imageUploadsUrl = import.meta.env.VITE_IMAGE_UPLOADS;
  const { data, isLoading } = useGetUserFavQuery(authenticateState.user.id);

  return (
    <div>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <div className="grid gap-4 grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5">
          {data?.map((recipe: any) => (
            <div
              key={recipe.id}
              className="p-4 border rounded shadow w-80 relative">
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
    </div>
  );
};

export default Favorites;
