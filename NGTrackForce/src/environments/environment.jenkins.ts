//This environment variable ensures that with each build running the ngtrackforce_test_deploy.sh will
//be routed to the correct URL and not break users developing on localhost


export const environment = {
  production: false,
  //url: "http://54.166.255.85:8085/"
  url: "http://52.87.205.55:8086/"
};

export const ngEnvironment = {
  production: false,
  url: "http://34.227.178.103:8090/"
};
