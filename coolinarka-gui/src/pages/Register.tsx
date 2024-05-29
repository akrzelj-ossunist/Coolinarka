import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import * as yup from "yup";
import { IRegister } from "../utils/interfaces";
import { ErrorMessage, Field, Form, Formik } from "formik";
import { RootState } from "../redux/store";
import { userRegister } from "../services/userRegister";

const Register: React.FC = () => {
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const navigate = useNavigate();
  const [exist, setExist] = useState(false);

  useEffect(() => {
    authenticateState.success && navigate("/Home");
  }, [authenticateState.success]);

  const initialValues: IRegister = {
    firstName: "",
    lastName: "",
    username: "",
    birthday: "",
    email: "",
    password: "",
    confirmPassword: "",
    bio: "",
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
    email: yup
      .string()
      .email("Enter a valid email")
      .required("Email is required"),
    password: yup
      .string()
      .min(8, "Password should be of minimum 8 characters length")
      .required("Password is required"),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref("password")], "Password must match"),
    bio: yup.string().max(500, "Bio is too long"),
  });

  const handleSubmit = async (values: any) => {
    try {
      const data = await userRegister(values);
      navigate("/Login");
    } catch (error) {
      setExist(true);
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
      label: "Email",
      type: "text",
      name: "email",
    },
    {
      label: "Password",
      type: "password",
      name: "password",
    },
    {
      label: "Confirm password",
      type: "password",
      name: "confirmPassword",
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
          Register page
        </h1>
        {exist && (
          <div
            className="bg-red-400 w-[400px] mx-5 mt-10 text-white p-4 rounded-lg shadow-md phone:w-[350px]"
            role="alert">
            <strong className="font-bold">
              User with inputed email already exists!
            </strong>
            <span className="block sm:inline">
              If you already have account...<Link to={"/Login"}>Login</Link>
            </span>
          </div>
        )}
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={(values) => {
            console.log(values);
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
                Register
              </button>
              <div className="flex items-center mt-2">
                <p>Already have account... </p>
                <Link
                  to="/Login "
                  className="text-sm font-bold text-[#11999e] underline cursor-pointer ml-1">
                  Login here
                </Link>
              </div>
            </div>
          </Form>
        </Formik>
      </div>
    </div>
  );
};

export default Register;
