import { useState } from "react";
import { FaStar } from "react-icons/fa";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { addReview } from "../services/addReview";
import useGetRecipeReviewsQuery from "../services/getRecipeReviews";
import axios from "axios";
import { useQueryClient } from "@tanstack/react-query";

const Reviews: React.FC<{ recipeId: string }> = ({ recipeId }) => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const [rating, setRating] = useState(0);
  const [hover, setHover] = useState(0);
  const [review, setReview] = useState("");
  const [error, setError] = useState(false);
  const [alreadyReviewed, setAlreadyReviewed] = useState(false);
  const queryClient = useQueryClient();

  const { data, isLoading } = useGetRecipeReviewsQuery(recipeId);

  const handleSubmit = async () => {
    try {
      if (review !== "" || rating !== 0) {
        setError(false);
        setAlreadyReviewed(false);
        setRating(0);
        setReview("");
        await addReview({
          user: authenticateState.user.id,
          recipe: recipeId,
          rating: rating,
          comment: review,
        });
        queryClient.invalidateQueries({ queryKey: ["reviews"] });
      } else setError(true);
    } catch (error) {
      setAlreadyReviewed(true);
    }
  };

  const handleDelete = async (id: string) => {
    try {
      await axios.delete(`http://localhost:8080/api/v1/review/${id}`, {
        headers: { "Content-Type": "application/json" },
      });
      queryClient.invalidateQueries({ queryKey: ["reviews"] });
    } catch (error) {
      console.error(error);
      throw error;
    }
  };

  !isLoading && console.log(data);
  return (
    <div className="border rounded shadow w-[97%] mt-4 p-4 pb-10">
      <p className="text-3xl font-semibold text-[#40514e] mb-3 border-b-2 pb-2">
        Reviews:{" "}
      </p>
      <div className="flex mt-6">
        {[...Array(5)].map((_, index) => {
          const currentRating = index + 1;
          return (
            <FaStar
              size={20}
              className="cursor-pointer"
              key={index}
              onClick={() => setRating(currentRating)}
              color={currentRating <= (hover || rating) ? "#ffc107" : "#e4e5e9"}
              onMouseEnter={() => setHover(currentRating)}
              onMouseLeave={() => setHover(0)}
            />
          );
        })}
        <div className="w-full relative">
          <input
            type="text"
            className="w-full border-b ml-4 focus:outline-none"
            value={review}
            placeholder="Rate and leave review..."
            onChange={(val) => setReview(val.target.value)}
          />
          {error && (
            <label htmlFor="" className="text-sm text-red-500 font-bold ml-4">
              Cannot submit review because either rating or review is empty...
            </label>
          )}
          {alreadyReviewed && (
            <label htmlFor="" className="text-sm text-red-500 font-bold ml-4">
              Current user already reviewed this recipe...
            </label>
          )}
          <button
            type="submit"
            onClick={handleSubmit}
            className="text-white cursor-pointer font-bold w-[140px] rounded-md bg-[#11999e] py-1 active:bg-blue-300 shadow-lg absolute right-0 top-10">
            Submit Review
          </button>
        </div>
      </div>
      <div className="mt-16">
        {isLoading ? (
          <p>Loading...</p>
        ) : (
          data.reviewPage.map((review: any) => {
            return (
              <div
                key={review.id}
                className="flex border-b w-full pb-2 relative">
                {[...Array(5)].map((_, index) => {
                  const currentRating = index + 1;
                  return (
                    <FaStar
                      key={index}
                      size={20}
                      color={
                        currentRating <= review.rating ? "#ffc107" : "#e4e5e9"
                      }
                    />
                  );
                })}
                <div className="flex flex-col ml-4 justify-center -mt-2">
                  <p className="font-semibold text-xs">
                    {review.user.username}
                  </p>
                  <p className="">{review.comment}</p>
                </div>
                {review.user.id === authenticateState.user.id && (
                  <button
                    type="submit"
                    onClick={() => handleDelete(review.id)}
                    className="text-white cursor-pointer font-bold w-[100px] rounded-md bg-red-600 py-1 active:bg-blue-300 shadow-lg absolute right-0">
                    Delete
                  </button>
                )}
              </div>
            );
          })
        )}
      </div>
    </div>
  );
};

export default Reviews;
