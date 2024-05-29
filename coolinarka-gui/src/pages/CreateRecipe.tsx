import { Formik, Form } from "formik";
import * as Yup from "yup";
import Introduction from "../components/RecipeForm.tsx/Introduction";
import Phases from "../components/RecipeForm.tsx/Phases";
import Tags from "../components/RecipeForm.tsx/Tags";
import { FormValues } from "../utils/interfaces";
import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { recipeCreate } from "../services/recipeCreate";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const CreateRecipe: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );

  const navigate = useNavigate();
  useEffect(() => {
    !authenticateState.success && navigate("/Login");
  }, [authenticateState.success]);

  const initialValues: FormValues = {
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
  };

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

  const handleSubmit = (value: FormValues) => {
    try {
      recipeCreate({ ...value, userId: authenticateState.user.id });
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={validationSchema}
      onSubmit={(values) => handleSubmit(values)}>
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
          className="text-white cursor-pointer font-bold w-[100px] rounded-xl text-xl bg-[#11999e] py-2 active:bg-blue-300 shadow-lg absolute right-24 top-16">
          Submit
        </button>
      </Form>
    </Formik>
  );
};

export default CreateRecipe;
