import axios from "axios";

export const userEdit = async (editForm: any, userId: string) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/v1/user/${userId}`,
      editForm,
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
