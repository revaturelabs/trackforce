//This environment variable ensures that with each build running the ngtrackforce_test_deploy.sh will
//be routed to the correct URL and not break users developing on localhost


export const environment = {
  production: false,
  url: "http://54.166.255.85:8085/"
};
