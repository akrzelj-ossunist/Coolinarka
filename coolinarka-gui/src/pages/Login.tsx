import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import * as yup from "yup";
import { ILogin } from "../utils/interfaces";
import { Field, Form, Formik } from "formik";
import { login } from "../redux/features/authentication/authenticate";
import { userLogin } from "../services/userLogin";
import { RootState, AppDispacth } from "../redux/store";

const Login: React.FC = () => {
  const dispatch = useDispatch<AppDispacth>();
  const authenticateState = useSelector(
    (state: RootState) => state.authenticate
  );
  const navigate = useNavigate();
  const [invalidLoginData, setInvalidLoginData] = useState(false);

  useEffect(() => {
    authenticateState.success && navigate("/Home");
  }, [authenticateState.success]);

  const initialValues: ILogin = {
    password: "",
    email: "",
  };

  const validationSchema = yup.object({
    email: yup
      .string()
      .email("Enter a valid email")
      .required("Email is required"),
    password: yup
      .string()
      .min(8, "Password should be of minimum 8 characters length")
      .required("Password is required"),
  });

  const handleSubmit = async (values: any) => {
    try {
      const data = await userLogin(values);
      dispatch(login(await data));
      console.log(authenticateState);
    } catch (error) {
      setInvalidLoginData(true);
    }
  };

  return (
    <div className="overflow-y-auto w-full flex flex-col justify-center ml-[5vw] tablet:items-center phone:ml-0 tablet:overflow-x-hidden">
      <div className="border-[2px] w-[500px] px-8 pb-8 pt-6 rounded-xl overflow-y-auto overflow-x-hidden phone:w-[350px] tablet:border-none phone:px-0">
        <h1 className="mx-5 font-bold text-4xl my-7 text-[#40514e]">
          Login page
        </h1>
        {invalidLoginData && (
          <div
            className="bg-red-400 w-[400px] mx-5 mt-10 text-white p-4 rounded-lg shadow-md phone:w-[350px]"
            role="alert">
            <strong className="font-bold">Incorrect Email or Password</strong>
            <span className="block sm:inline"> Please try again.</span>
          </div>
        )}
        <Formik
          initialValues={initialValues}
          validationSchema={validationSchema}
          onSubmit={(values) => {
            handleSubmit(values);
          }}>
          {({ errors, touched }) => {
            return (
              <Form className="my-10 w-[400px] phone:w-[350px] m-5">
                <div className="mb-10 flex flex-col">
                  <label className="text-[#40514e] font-semibold">Email:</label>
                  <Field
                    className="border-b-2 border-black p-2 text-lg focus:outline-none"
                    type="text"
                    name="email"
                  />
                  {errors.email && touched.email && (
                    <label className="text-sm text-red-500 font-bold">
                      {errors.email}
                    </label>
                  )}
                </div>
                <div className="flex flex-col mb-10">
                  <label className="text-[#40514e] font-semibold">
                    Password:
                  </label>
                  <Field
                    className="border-b-2 border-black p-2 text-lg focus:outline-none"
                    type="password"
                    name="password"
                  />
                  {errors.password && touched.password && (
                    <label className="text-sm text-red-500 font-bold">
                      {errors.password}
                    </label>
                  )}
                </div>
                <div className="flex flex-col">
                  <button
                    type="submit"
                    className="text-white cursor-pointer font-bold w-[200px] rounded-xl text-2xl bg-[#11999e] py-3 active:bg-blue-300 shadow-lg">
                    Login
                  </button>
                  <div className="flex items-center mt-2">
                    <p>Still dont have account... </p>
                    <Link
                      to="/Register "
                      className="text-sm font-bold text-[#11999e] underline cursor-pointer ml-1">
                      Register here
                    </Link>
                  </div>
                </div>
              </Form>
            );
          }}
        </Formik>
      </div>
    </div>
  );
};

export default Login;
