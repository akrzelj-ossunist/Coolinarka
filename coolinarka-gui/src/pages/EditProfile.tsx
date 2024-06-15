import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import * as yup from "yup";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { RootState } from "../redux/store";
import { format } from "date-fns";
import { userEdit } from "../services/userEdit";

const EditProfile: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const navigate = useNavigate();

  useEffect(() => {
    !authenticateState.success && navigate("/Login");
  }, [authenticateState.success]);

  const initialValues = {
    firstName: authenticateState.user.firstName,
    lastName: authenticateState.user.lastName,
    username: authenticateState.user.username,
    birthday: format(new Date(authenticateState.user.birthday!), "yyyy-MM-dd"),
    bio: authenticateState.user.bio,
  };

  const validationSchema = yup.object({
    firstName: yup
      .string()
      .min(2, "First name should be of minimum 2 characters length")
      .required("First name is required"),
    lastName: yup
      .string()
      .min(2, "Last name should be of minimum 2 characters length")
      .required("Last name is required"),
    username: yup
      .string()
      .min(6, "Username should be of minimum 6 characters length")
      .required("Username is required"),
    birthday: yup.date().required("You need to fill this box").nullable(),
    bio: yup.string().max(500, "Bio is too long"),
  });

  const handleSubmit = async (values: any) => {
    try {
      await userEdit(values, authenticateState.user.id);
      navigate("/Profile");
    } catch (error) {
      console.error(error);
    }
  };

  const formData = [
    {
      label: "First name",
      type: "text",
      name: "firstName",
    },
    {
      label: "Last name",
      type: "text",
      name: "lastName",
    },
    {
      label: "Username",
      type: "text",
      name: "username",
    },
    {
      label: "Birthday",
      type: "date",
      name: "birthday",
    },
    {
      label: "Bio",
      type: "textarea",
      name: "bio",
    },
  ];

  return (
    <div className="overflow-y-auto w-full flex flex-col justify-center ml-[5vw] tablet:items-center phone:ml-0 tablet:overflow-x-hidden">
      <div className="border-[2px] w-[500px] px-8 pb-8 pt-6 rounded-xl overflow-y-auto overflow-x-hidden phone:w-[350px] tablet:border-none phone:px-0">
        <h1 className="mx-5 font-bold text-4xl my-7 text-[#40514e]">
          Edit profile
        </h1>
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={(values) => {
            handleSubmit(values);
          }}>
          <Form className="my-10 w-[400px] phone:w-[350px] m-5">
            {formData.map((data, index) => {
              return (
                <div key={index} className="flex flex-col mb-10">
                  <label className="text-[#40514e] font-semibold">
                    {data.label}:
                  </label>
                  <Field
                    className="border-b-2 border-black p-2 text-lg focus:outline-none"
                    type={data.type}
                    name={data.name}
                    as={data.type === "textarea" ? "textarea" : "input"}
                  />
                  <ErrorMessage
                    name={data.name}
                    component="div"
                    className="text-sm text-red-500 font-bold"
                  />
                </div>
              );
            })}
            <div className="flex flex-col">
              <button
                type="submit"
                className="text-white cursor-pointer font-bold w-[200px] rounded-xl text-2xl bg-[#11999e] py-3 active:bg-blue-300 shadow-lg">
                Edit
              </button>
            </div>
          </Form>
        </Formik>
      </div>
    </div>
  );
};

export default EditProfile;
