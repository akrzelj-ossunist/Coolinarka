import React from "react";
import { Field, ErrorMessage, useFormikContext } from "formik";
import {
  countries,
  difficulty,
  events,
  mealGroup,
  seasons,
} from "../../utils/data";
import Select from "react-select";
import { FormValues } from "../../utils/interfaces";

const Tags: React.FC = () => {
  const { values, setFieldValue } = useFormikContext<FormValues>();

  return (
    <div className="flex flex-col">
      <div className="flex">
        <label htmlFor="people">People: </label>
        <Field
          name="people"
          type="number"
          min="1"
          className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none"
        />
        <ErrorMessage
          name="people"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div className="flex">
        <label htmlFor="prepTime">Preparation Time</label>
        <Field
          name="prepTime"
          type="number"
          min="1"
          className="border-[1px] rounded-md border-slate-200 bg-white text-sm p-2 shadow-sm w-full focus:outline-none"
        />
        <ErrorMessage
          name="prepTime"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div>
        <Field name="difficulty">
          {({ field }: any) => (
            <Select
              {...field}
              options={difficulty}
              onChange={(option: any) =>
                setFieldValue("difficulty", option.value)
              }
              value={difficulty.find(
                (option) => option.value === values.difficulty
              )}
              placeholder="Select season"
            />
          )}
        </Field>
        <ErrorMessage
          name="difficulty"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div>
        <Field name="country">
          {({ field }: any) => (
            <Select
              {...field}
              options={countries}
              onChange={(option: any) => setFieldValue("country", option.value)}
              value={countries.find(
                (option) => option.value === values.country
              )}
              placeholder="Select Country"
            />
          )}
        </Field>
        <ErrorMessage
          name="country"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div>
        <Field name="mealGroup">
          {({ field }: any) => (
            <Select
              {...field}
              closeMenuOnSelect={false}
              options={mealGroup}
              onChange={(options: any) => {
                const value = options.map((option: any) => option.value);
                setFieldValue("mealGroup", value);
              }}
              value={mealGroup.filter((option) =>
                values.mealGroup.includes(option.value)
              )}
              isMulti
              placeholder="Select meal groups"
            />
          )}
        </Field>
        <ErrorMessage
          name="mealGroup"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div>
        <Field name="events">
          {({ field }: any) => (
            <Select
              {...field}
              closeMenuOnSelect={false}
              options={events}
              onChange={(options: any) => {
                const value = options.map((option: any) => option.value);
                setFieldValue("events", value);
              }}
              value={events.filter((option) =>
                values.events.includes(option.value)
              )}
              isMulti
              placeholder="Select event"
            />
          )}
        </Field>
        <ErrorMessage
          name="events"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
      <div>
        <Field name="season">
          {({ field }: any) => (
            <Select
              {...field}
              options={seasons}
              onChange={(option: any) => setFieldValue("season", option.value)}
              value={seasons.find((option) => option.value === values.season)}
              placeholder="Select season"
            />
          )}
        </Field>
        <ErrorMessage
          name="season"
          component="div"
          className="text-sm text-red-500 font-bold"
        />
      </div>
    </div>
  );
};

export default Tags;
