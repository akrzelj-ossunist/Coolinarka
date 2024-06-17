import { useState } from "react";
import RecipeFilters from "../components/RecipeFilters";
import RecipeList from "../components/RecipeList";

const Home: React.FC = () => {
  const [filters, setFilters] = useState({
    name: "",
    country: "",
    season: "",
    difficulty: "",
    ingredients: "",
    userId: "",
    sort: "",
  });

  return (
    <div className="w-full">
      <RecipeFilters setFilters={setFilters} />
      <RecipeList filters={filters} />
    </div>
  );
};

export default Home;
