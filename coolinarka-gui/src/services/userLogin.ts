import axios from "axios";

export const userLogin = async (loginData: any) => {
  console.log(loginData);
  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/user/login",
      loginData,
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
