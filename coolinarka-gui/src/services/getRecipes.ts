import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getRecipes = async (filters: any) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/recipe/filter`,
      {
        params: {
          name: filters.name,
          country: filters.country,
          season: filters.season,
          difficulty: filters.difficulty,
          ingredients: filters.ingredients,
          userId: filters.userId,
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const useGetRecipesQuery = (filters: any) => {
  return useQuery({ queryKey: [filters], queryFn: () => getRecipes(filters) });
};

export default useGetRecipesQuery;
