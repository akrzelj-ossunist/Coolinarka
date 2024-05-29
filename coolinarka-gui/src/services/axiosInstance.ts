import axios from "axios";
import { getJwtTokenFromLocalStorage } from "../utils/getJwtFromLocalStorage";

export default axios.create({
  headers: {
    "Content-Type": "application/json",
    Authentication: `Bearer ${getJwtTokenFromLocalStorage()}`,
    Authorization: `Bearer ${getJwtTokenFromLocalStorage()}`,
  },
});
