import axios from "axios";
import { IRegister } from "../utils/interfaces";

export const userRegister = async (registerForm: IRegister) => {
  try {
    const response = await axios.post(
      "http://localhost:8080/api/v1/user/register",
      registerForm,
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
