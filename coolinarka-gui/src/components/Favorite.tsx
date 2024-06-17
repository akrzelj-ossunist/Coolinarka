import {
  IconCheckedFavorite,
  IconUncheckedFavorite,
} from "../assets/FavoriteIcon";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import axios from "axios";
import useGetUserFavQuery from "../services/getUserFavorites";
import { useQueryClient } from "@tanstack/react-query";

const Favorite: React.FC<{ recipeId: string }> = ({ recipeId }) => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const { data: favData, isLoading: loading } = useGetUserFavQuery(
    authenticateState.user.id
  );

  const queryClient = useQueryClient();

  const handleAddToFavorites = async (recipeId: string) => {
    try {
      await axios.post(
        `http://localhost:8080/api/v1/favorite/`,
        {
          recipe: recipeId,
          user: authenticateState.user.id,
        },
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      queryClient.invalidateQueries({ queryKey: ["fav"] });
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  const handleRemoveFromFavorites = async (recipeId: string) => {
    try {
      await axios.delete(
        `http://localhost:8080/api/v1/favorite/recipe/${recipeId}/user/${authenticateState.user.id}`,
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      queryClient.invalidateQueries({ queryKey: ["fav"] });
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  const containsRecipe = (recipeId: string) => {
    return favData.some((data: any) => data.id === recipeId);
  };

  return (
    <>
      {!loading && containsRecipe(recipeId) ? (
        <IconCheckedFavorite
          className="absolute right-0 bottom-0 cursor-pointer"
          onClick={() => handleRemoveFromFavorites(recipeId)}
        />
      ) : (
        <IconUncheckedFavorite
          className="absolute right-0 bottom-0 cursor-pointer"
          onClick={() => handleAddToFavorites(recipeId)}
        />
      )}
    </>
  );
};

export default Favorite;
