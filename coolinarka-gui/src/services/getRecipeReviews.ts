import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getRecipeReviews = async (filters: any) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/review/list/recipe/${filters.recipeId}`,
      {
        params: {
          page: filters.page,
        },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const useGetRecipeReviewsQuery = (filters: any) => {
  return useQuery({
    queryKey: ["reviews", filters],
    queryFn: () => getRecipeReviews(filters),
  });
};

export default useGetRecipeReviewsQuery;
