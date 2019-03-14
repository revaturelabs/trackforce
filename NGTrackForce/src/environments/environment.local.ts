// This file will be used instead of environment.ts when you use the 
// command: ng serve --configuration=local
// This is intended to be used when testing the application and both the frontend+backend are running
// on the local machine.

// export const environment = {
//   production: false,
//   url: "http://localhost:8085/"
// };

export const environment = {
  production: false,
  // url: "http://localhost:8080/"
  url: "http://3.84.1.206:8086/"
};

export const ngEnvironment = {
  production: false,
  url: "http://localhost:4200/"
};
