import { useDispatch, useSelector } from "react-redux";
import { RootState, AppDispacth } from "../redux/store";
import { useState } from "react";
import RecipeFilters from "../components/RecipeFilters";
import RecipeList from "../components/RecipeList";

const Home: React.FC = () => {
  const dispatch = useDispatch<AppDispacth>();
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const [filters, setFilters] = useState({
    name: "",
    country: "",
    season: "",
    difficulty: "",
    ingredients: "",
    userId: "",
  });

  return (
    <div className="w-full">
      <RecipeFilters setFilters={setFilters} />
      <RecipeList filters={filters} />
    </div>
  );
};

export default Home;
