export const getJwtTokenFromLocalStorage = () => {
  // Retrieve the 'persist:main-root' data from localStorage
  const persistDataString = localStorage.getItem("persist:main-root");

  if (persistDataString) {
    try {
      // Parse the JSON string to get the object
      const persistData = JSON.parse(persistDataString);
      const authenticateData = JSON.parse(persistData.authenticate);

      // Extract the jwtToken
      return authenticateData.jwtToken;
    } catch (error) {
      console.error("Error parsing JSON from localStorage", error);
      return null;
    }
  } else {
    console.log('No data found in localStorage for key "persist:main-root"');
    return null;
  }
};
