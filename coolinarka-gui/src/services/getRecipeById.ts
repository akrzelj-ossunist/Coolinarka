import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getRecipeById = async (id: string) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/recipe/${id}`
    );
    return response.data;
  } catch (error) {
    console.error(error);
  }
};

const useGetRecipeByIdQuery = (id: string) => {
  return useQuery({ queryKey: [id], queryFn: () => getRecipeById(id) });
};

export default useGetRecipeByIdQuery;
