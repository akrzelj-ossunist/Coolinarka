import React from "react";
import { Field, FieldArray, ErrorMessage, useFormikContext } from "formik";
import { FormValues } from "../../utils/interfaces";

const Phases: React.FC = () => {
  const { values } = useFormikContext<FormValues>();

  return (
    <div className="flex flex-col">
      <FieldArray name="ingredients">
        {({ remove, push }) => (
          <div className="flex flex-col">
            <p className="font-bold text-2xl border-b-2 border-[#40514e] w-36 m-4 text-[#40514e]">
              Ingredients:
            </p>
            {values.ingredients.length > 0 &&
              values.ingredients.map((_, index) => (
                <div key={index} className="flex justify-evenly my-2">
                  <div className="w-[50%]">
                    <Field
                      name={`ingredients.${index}.ingredient`}
                      type="text"
                      placeholder="Ingredient"
                      className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none"
                    />
                    <ErrorMessage
                      name={`ingredients.${index}.ingredient`}
                      component="div"
                      className="text-sm text-red-500 font-bold"
                    />
                  </div>
                  <div className="w-[15%]">
                    <Field
                      name={`ingredients.${index}.amount`}
                      type="number"
                      min="1"
                      placeholder="Amount"
                      className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none"
                    />
                    <ErrorMessage
                      name={`ingredients.${index}.amount`}
                      component="div"
                      className="text-sm text-red-500 font-bold"
                    />
                  </div>
                  <div className="w-[15%]">
                    <Field
                      name={`ingredients.${index}.unit`}
                      type="text"
                      placeholder="Unit"
                      className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none"
                    />
                    <ErrorMessage
                      name={`ingredients.${index}.unit`}
                      component="div"
                      className="text-sm text-red-500 font-bold"
                    />
                  </div>
                  <div className="w-[10%]">
                    <button
                      type="button"
                      className="text-white cursor-pointer font-bold w-[50px] rounded-xl text-lg bg-red-500 py-1 active:bg-red-700 shadow-lg"
                      onClick={() => remove(index)}>
                      X
                    </button>
                  </div>
                </div>
              ))}
            <button
              type="button"
              className="text-white cursor-pointer font-bold w-[50px] rounded-xl text-lg bg-[#11999e] py-1 active:bg-blue-300 shadow-lg m-4"
              onClick={() =>
                push({ ingredientName: "", amount: "", unit: "" })
              }>
              +
            </button>
          </div>
        )}
      </FieldArray>
      <FieldArray name="phases">
        {({ remove, push }) => (
          <div className="flex flex-col">
            <p className="font-bold text-2xl border-b-2 border-[#40514e] w-36 m-4 text-[#40514e]">
              Phases:
            </p>
            {values.phases.length > 0 &&
              values.phases.map((_, index) => (
                <div key={index} className="flex justify-evenly">
                  <Field
                    name={`phases.${index}.index`}
                    type="text"
                    value={index + 1}
                    readOnly
                    className="hidden"
                  />
                  <div className="w-[80%]">
                    <Field
                      name={`phases.${index}.description`}
                      type="text"
                      as="textarea"
                      className="border-[1px] h-24 rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none my-1"
                      placeholder="Phase description..."
                    />
                    <ErrorMessage
                      name={`phases.${index}.description`}
                      component="div"
                      className="text-sm text-red-500 font-bold"
                    />
                  </div>
                  <div className="w-[10%]">
                    <button
                      type="button"
                      className="text-white cursor-pointer font-bold w-[50px] rounded-xl text-lg bg-red-500 py-1 active:bg-red-700 shadow-lg"
                      onClick={() => remove(index)}>
                      X
                    </button>
                  </div>
                </div>
              ))}
            <button
              type="button"
              className="text-white cursor-pointer font-bold w-[50px] rounded-xl text-lg bg-[#11999e] py-1 active:bg-blue-300 shadow-lg mx-6 mt-3"
              onClick={() =>
                push({ index: values.phases.length, description: "" })
              }>
              +
            </button>
          </div>
        )}
      </FieldArray>
    </div>
  );
};

export default Phases;
