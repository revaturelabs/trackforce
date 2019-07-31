//This environment variable ensures that with each build running the ngtrackforce_test_deploy.sh will
//be routed to the correct URL and not break users developing on localhost


export const environment = {
  production: false,
  url: "http://localhost:8080/"
  //url: "http://3.84.1.206:8086/"
};

export const ngEnvironment = {
  production: false,
  url: "http://trackforce.revaturelabs.com/"
};
