import { useState } from "react";
import { ErrorMessage, useFormikContext } from "formik";
import noImage from "../../assets/noImage.jpg";

const ImageUpload = () => {
  const [imageUrl, setImageUrl] = useState<any>(noImage);
  const { setFieldValue } = useFormikContext();

  const handleImageChange = (event: any) => {
    const file = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setImageUrl(reader.result);
        setFieldValue("image", file);
      };
      reader.readAsDataURL(file);
    } else {
      setImageUrl(noImage);
    }
  };

  return (
    <div className="mb-10 flex flex-col">
      <label className="border-b-2 border-black p-2 text-lg focus:outline-none">
        <span className="cursor-pointer bg-[#40514e] rounded-lg -ml-2 text-white font-semibold shadow-xl px-4 py-2">
          Choose File
        </span>
        <input
          name="image"
          type="file"
          accept="image/jpeg, image/jpg"
          style={{ display: "none" }}
          onChange={handleImageChange}
        />
      </label>
      <ErrorMessage
        name="image"
        component="div"
        className="text-sm text-red-500 font-bold"
      />
      <img src={imageUrl} alt="Selected" className="mt-4 h-56 object-cover" />
    </div>
  );
};

export default ImageUpload;
