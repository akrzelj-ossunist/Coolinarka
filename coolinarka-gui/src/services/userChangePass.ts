import axios from "axios";

export const userChangePass = async (form: any, userId: string) => {
  try {
    const response = await axios.put(
      `http://localhost:8080/api/v1/user/change-password/${userId}`,
      form,
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
