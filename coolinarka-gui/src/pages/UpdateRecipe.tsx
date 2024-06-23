import { Formik, Form } from "formik";
import * as Yup from "yup";
import Introduction from "../components/RecipeForm.tsx/Introduction";
import Phases from "../components/RecipeForm.tsx/Phases";
import Tags from "../components/RecipeForm.tsx/Tags";
import { FormValues } from "../utils/interfaces";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useGetRecipeByIdQuery from "../services/getRecipeById";
import { updateRecipe } from "../services/updateRecipe";

const UpdateRecipe: React.FC = () => {
  const { recipeId } = useParams();
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );

  const navigate = useNavigate();
  const { data, isLoading } = useGetRecipeByIdQuery(recipeId || "");

  useEffect(() => {
    if (!authenticateState.success) {
      navigate("/Login");
    }
  }, [authenticateState.success, navigate]);

  const [initialValues, setInitialValues] = useState<FormValues>({
    image: "",
    name: "",
    description: "",
    ingredients: [
      {
        ingredient: "",
        amount: "",
        unit: "",
      },
    ],
    phases: [
      {
        index: 0,
        description: "",
      },
    ],
    people: 0,
    prepTime: 0,
    difficulty: "",
    country: "",
    mealGroup: [],
    events: [],
    season: "",
  });

  useEffect(() => {
    if (!isLoading && data) {
      setInitialValues({
        image: "",
        name: data.name,
        description: data.description,
        ingredients: data.ingredients.map((el: any) => ({
          ingredient: el.ingredient,
          amount: el.amount,
          unit: el.unit,
        })),
        phases: data.phases.map((el: any) => ({
          index: el.index,
          description: el.description,
        })),
        people: data.people,
        prepTime: data.prepTime,
        difficulty: data.difficulty,
        country: data.country,
        mealGroup: data.mealGroups.map((el: any) => el),
        events: data.events.map((el: any) => el),
        season: data.season,
      });
    }
  }, [isLoading, data]);

  const validationSchema = Yup.object({
    image: Yup.mixed().required("Required"),
    name: Yup.string().required("Required"),
    description: Yup.string().required("Required"),
    ingredients: Yup.array().of(
      Yup.object({
        ingredient: Yup.string().required("Required"),
        amount: Yup.number()
          .positive("Must be a positive number")
          .required("Required"),
        unit: Yup.string().required("Required"),
      })
    ),
    phases: Yup.array().of(
      Yup.object({
        index: Yup.number().required("Required"),
        description: Yup.string().required("Required"),
      })
    ),
    people: Yup.number()
      .positive("Must be a positive number")
      .required("Required"),
    prepTime: Yup.number()
      .positive("Must be a positive number")
      .required("Required"),
    difficulty: Yup.string().required("Required"),
    country: Yup.string().required("Required"),
    mealGroup: Yup.array().of(Yup.string()).required("Required"),
    events: Yup.array().of(Yup.string()).required("Required"),
    season: Yup.string().required("Required"),
  });

  const handleSubmit = async (values: FormValues) => {
    try {
      await updateRecipe({ ...values, userId: authenticateState.user.id }, recipeId || "");
      navigate(`/Home`);
    } catch (error) {
      console.error(error);
    }
  };

  return isLoading ? (
    <p>Loading...</p>
  ) : (
    <Formik
      initialValues={initialValues}
      enableReinitialize={true}
      validationSchema={validationSchema}
      onSubmit={(values) => handleSubmit(values)}
    >
      <Form className="grid grid-cols-12 gap-4 w-full">
        <div className="col-span-6">
          <Introduction />
          <Tags />
        </div>
        <div className="col-span-6">
          <Phases />
        </div>
        <button
          type="submit"
          className="text-white cursor-pointer font-bold w-[100px] rounded-xl text-xl bg-[#11999e] py-2 active:bg-blue-300 shadow-lg absolute right-24 top-16"
        >
          Submit
        </button>
      </Form>
    </Formik>
  );
};

export default UpdateRecipe;
