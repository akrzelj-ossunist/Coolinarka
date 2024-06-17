import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getRecipeReviews = async (recipe: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/review/list/recipe/${recipe}`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const useGetRecipeReviewsQuery = (recipe: any) => {
  return useQuery({
    queryKey: ["reviews"],
    queryFn: () => getRecipeReviews(recipe),
  });
};

export default useGetRecipeReviewsQuery;
