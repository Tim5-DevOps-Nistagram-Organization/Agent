import React, { useEffect, useState } from "react";
import PropTypes, { number, string } from "prop-types";
import { Button } from "@material-ui/core";

const ImageInput = ({
  name,
  label,
  imageUploaded,
  onChange,
  onUpload,
  value,
}) => {
  const [oldValue, setOldValue] = useState("");
  const [newValue, setNewValue] = useState("");

  useEffect(() => {
    setOldValue(value);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const toBase64 = (file) =>
    new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result);
      reader.onerror = (error) => reject(error);
    });

  async function loadImage(file) {
    return await toBase64(file);
  }

  function handleChange(event) {
    const { files } = event.target;
    loadImage(files[0]).then((data) => setNewValue(data));
    onChange(event);
  }

  return (
    <>
      <Button
        variant="contained"
        component="label"
        color={"primary"}
        style={{ width: "49%", marginRight: "1%", marginTop: "2px" }}
      >
        {label}
        <input
          name={name}
          onChange={handleChange}
          type="file"
          hidden
          accept="image/jpeg, image/png"
        />
      </Button>
      {!imageUploaded ? (
        <Button
          variant="contained"
          component="label"
          color={"primary"}
          style={{ width: "49%", marginLeft: "1%", marginTop: "2px" }}
          onClick={onUpload}
        >
          Upload image
        </Button>
      ) : (
        newValue !== "" && (
          <p>{"New image is uploaded if you want to save it press save."}</p>
        )
      )}
      <br />
      {oldValue !== "" && (
        <img
          src={oldValue}
          alt="Product doesn't have old value"
          style={{ width: "49%", marginRight: "1%", marginTop: "2px" }}
        />
      )}
      {newValue !== "" && (
        <img
          src={newValue}
          alt="Product doesn't have new value"
          style={{ width: "49%", marginLeft: "1%", marginTop: "2px" }}
        />
      )}
    </>
  );
};

ImageInput.propTypes = {
  name: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  imageUploaded: PropTypes.bool.isRequired,
  onChange: PropTypes.func.isRequired,
  onUpload: PropTypes.func.isRequired,
  value: PropTypes.oneOfType([string, number]),
};

export default ImageInput;
