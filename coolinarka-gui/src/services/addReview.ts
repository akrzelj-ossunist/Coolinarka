import axios from "axios";

export const addReview = async (data: any) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/review/create",
      data,
      {
        headers: { "Content-Type": "application/json" },
      }
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
