//This environment variable ensures that with each build running the ngtrackforce_test_deploy.sh will
//be routed to the correct URL and not break users developing on localhost


export const environment = {
  production: false,
  url: "http://52.201.234.9:8085/"
};
