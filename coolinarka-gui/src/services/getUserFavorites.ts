import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getUserFavorites = async (userId: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/favorite/list/user/${userId}`
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const useGetUserFavQuery = (id: string) => {
  return useQuery({
    queryKey: ["fav", id],
    queryFn: () => getUserFavorites(id),
  });
};

export default useGetUserFavQuery;
