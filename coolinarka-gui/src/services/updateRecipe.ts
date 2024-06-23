import axios from "axios";

export const updateRecipe = async (data: any, recipeId: string) => {
  const formData = new FormData();
  formData.append("image", data.image);

  const recipeData = {
    name: data.name,
    description: data.description,
    user: data.userId,
    country: data.country,
    difficulty: data.difficulty,
    people: data.people,
    prepTime: data.prepTime,
    ingredients: data.ingredients,
    phases: data.phases,
    mealGroup: data.mealGroup,
    season: data.season,
    events: data.events,
  };

  console.log(recipeData);
  formData.append(
    "recipe",
    new Blob([JSON.stringify(recipeData)], {
      type: "application/json",
    })
  );

  try {
    const config = {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    };

    const response = await axios.put(
      `http://localhost:8080/api/v1/recipe/${recipeId}`,
      formData,
      config
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error(error);
    throw error;
  }
};
