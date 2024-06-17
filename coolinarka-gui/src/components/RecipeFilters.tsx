import { countries, difficulty, seasons, sortData } from "../utils/data";
import Select from "react-select";
import { useState } from "react";
import IconAddCircle from "../assets/Add";

const RecipeFilters: React.FC<{
  setFilters: (val: any) => void;
}> = ({ setFilters }) => {
  const [newFilters, setNewFilters] = useState({
    name: "",
    country: "",
    season: "",
    difficulty: "",
    ingredients: "",
    userId: "",
    sort: "",
  });

  const [ingredient, setIngredient] = useState("");
  const saveIngredient = () => {
    if (ingredient !== "") {
      const newIngredientList = newFilters.ingredients + ingredient + ",";
      setNewFilters({ ...newFilters, ingredients: newIngredientList });
      setIngredient("");
    }
  };

  const handleRemoveIngredient = (ingredientToRemove: any) => {
    const updatedIngredients = ingredient
      .split(",") // Convert string to array
      .filter((ingredient) => ingredient !== ingredientToRemove) // Remove the ingredient
      .join(","); // Convert back to string

    console.log(updatedIngredients);
    setNewFilters({ ...newFilters, ingredients: updatedIngredients });
  };

  const handleOnSubmit = () => {
    setNewFilters({
      name: "",
      country: "",
      season: "",
      difficulty: "",
      ingredients: "",
      userId: "",
      sort: "",
    });
    setFilters(newFilters);
  };

  return (
    <div className="p-4 border rounded shadow my-5 mb-10">
      <div className="flex w-full justify-between flex-wrap">
        <Select
          options={countries}
          onChange={(option: any) =>
            setNewFilters({ ...newFilters, country: option.value })
          }
          placeholder="Select Country"
          className="w-[170px] my-2"
        />
        <Select
          options={seasons}
          onChange={(option: any) =>
            setNewFilters({ ...newFilters, season: option.value })
          }
          placeholder="Select season"
          className="w-[170px] my-2"
        />
        <Select
          options={difficulty}
          onChange={(option: any) =>
            setNewFilters({ ...newFilters, difficulty: option.value })
          }
          className="w-[170px] my-2"
          placeholder="Select difficulty"
        />
        <Select
          options={sortData}
          onChange={(option: any) =>
            setNewFilters({ ...newFilters, sort: option.value })
          }
          className="w-[170px] my-2"
          placeholder="Sort by"
        />
        <div className="relative">
          <input
            placeholder="Ingredient..."
            value={ingredient}
            onChange={(e) => setIngredient(e.target.value)}
            className="px-2 rounded-[4px] border-lightGray border-[1px] pr-9 w-[170px] h-[38px] my-2"
          />
          <IconAddCircle
            className="absolute text-slate-400 cursor-pointer top-4 right-2"
            onClick={saveIngredient}
          />
        </div>
        <input
          placeholder="Recipe name..."
          onChange={(option: any) =>
            setNewFilters({ ...newFilters, name: option.target.value })
          }
          className="px-2 rounded-[4px] border-lightGray border-[1px] pr-9 w-[170px] h-[38px] my-2"
        />
        <button
          className="text-white cursor-pointer font-bold w-[170px] rounded-[4px] text-xl bg-[#11999e] px-2 h-[38px] my-2"
          onClick={handleOnSubmit}>
          Submit
        </button>
      </div>
      <div className="flex w-full mt-5 flex-wrap">
        {newFilters.ingredients.split(",").map((ingredient, index) => {
          return (
            ingredient !== "" && (
              <div
                key={index}
                className="relative flex w-auto border rounded shadow mr-4 px-2 pr-10 py-1 my-2">
                <p>{ingredient}</p>
                <p
                  className="font-bold absolute right-2 cursor-pointer"
                  onClick={() => handleRemoveIngredient(ingredient)}>
                  X
                </p>
              </div>
            )
          );
        })}
      </div>
    </div>
  );
};

export default RecipeFilters;
