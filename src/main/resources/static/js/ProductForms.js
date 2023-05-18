document
  .getElementById("inputImage")
  .addEventListener("change", function (event) {
    const file = event.target.files[0];
    const reader = new FileReader();

    reader.onloadend = function () {
      const base64 = reader.result;
      document.getElementById("outputBase64").value = base64;
    };

    reader.readAsDataURL(file);
  });
