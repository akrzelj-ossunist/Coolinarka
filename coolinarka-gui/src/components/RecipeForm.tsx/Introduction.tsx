import React from "react";
import { Field, ErrorMessage, useFormikContext } from "formik";
import { FormValues } from "../../utils/interfaces";
import ImageUpload from "./ImageUpload";

const Introduction: React.FC = () => {
  const { values } = useFormikContext<FormValues>();
  console.log(values);
  return (
    <div className="flex flex-col">
      <ImageUpload />
      <div className="mb-10 flex flex-col">
        <label htmlFor="name" className="text-[#40514e] font-semibold">
          Name
        </label>
        <Field
          name="name"
          type="text"
          className="border-b-2 border-black p-2 text-lg focus:outline-none"
        />
        <ErrorMessage
          name="name"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div className="mb-10 flex flex-col">
        <label htmlFor="description" className="text-[#40514e] font-semibold">
          Description
        </label>
        <Field
          name="description"
          type="text"
          as="textarea"
          className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 h-40 shadow-sm focus:outline-none"
        />
        <ErrorMessage
          name="description"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
    </div>
  );
};

export default Introduction;
