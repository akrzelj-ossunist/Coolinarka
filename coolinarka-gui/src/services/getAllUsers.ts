import { useQuery } from "@tanstack/react-query";
import axios from "axios";

const getAllUsers = async (filters: any) => {
  try {
    const response = await axios.get(
      `http://localhost:8080/api/v1/user/list`,
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

const useGetAllUsersQuery = (filters: any) => {
  return useQuery({
    queryKey: ["userList", filters],
    queryFn: () => getAllUsers(filters),
  });
};

export default useGetAllUsersQuery;
