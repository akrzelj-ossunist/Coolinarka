import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const DeleteRecipe: React.FC = () => {
  const { recipeId } = useParams();
  const navigate = useNavigate();

  const handleDeleteRecipe = async () => {
    try {
      await axios.delete(
        `http://localhost:8080/api/v1/recipe/${recipeId}`,

        {
          headers: { "Content-Type": "application/json" },
        }
      );
      navigate("/Profile");
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  return (
    <div className="flex w-full justify-center bg-gray-100">
      <div className="max-w-md p-4 bg-white rounded shadow-md">
        <h2 className="text-2xl font-bold mb-4">Delete Recipe</h2>
        <p className="text-lg mb-4">
          Are you sure you want to delete recipe? That action is ireversable and
          cannot be undone!
        </p>
        <div className="flex justify-between">
          <button
            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
            onClick={() => {
              handleDeleteRecipe();
            }}>
            Delete
          </button>
          <button
            className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded"
            onClick={() => {
              navigate("/Profile");
            }}>
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};

export default DeleteRecipe;
