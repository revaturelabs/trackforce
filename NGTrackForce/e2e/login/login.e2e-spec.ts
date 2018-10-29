import { LoginPage } from './login.po';
import { TestConfig } from '../configuration/test-config';

/*
Smoke test: Checks elements to be exist
*/
let AdminUsername = "TestAdmin";
let AdminPassword = "TestAdmin";
let associateUsername = "cyril";
let associatePassword = "cyril";
let stagingManagerUsername = "bobstage";
let stagingManagerPassword = "bobstage";
let trainerUsername = "Trainer0";
let trainerPassword = "Trainer";
let deliverySalesUsername = "salestest";
let deliverySalesPassword = "salestest";
let page: LoginPage;
let testConfig      : TestConfig;
let baseURL         : string;

describe('login page element existences', () => {


  beforeAll(() => {
    page = new LoginPage();
    page.navigateTo();
  });

  it('should have a username input box', () => {
    expect(page.getUsernameInput()).toBeTruthy();
  });

  it('should have a password input box', () => {
    expect(page.getPasswordInput()).toBeTruthy();
  });

  it('should have a login button', () => {
    expect(page.getLoginButton()).toBeTruthy();
  });

  it('should display proper placeholder: Username', () => {
    expect(page.getUsernamePlaceholder()).toEqual('Username');
  });

  it('should display proper placeholder: Password', () => {
    expect(page.getPasswordPlaceholder()).toEqual('Password');
  });

  it('should display proper value for button', () => {
    expect(page.getLoginButtonText()).toEqual('SIGN IN');
  });
});

describe('Confirm login failures', () => {

  beforeAll(() => {
    page = new LoginPage();
    page.navigateTo();
  });

  it('should fail to log in when nothing is entered', () => {
    page.getLoginButton().click();
    expect(page.getFailedLoginResponse()).toContain('Please enter a username and password');
  });

  it('should fail to login when incorrect credentials are entered', () => {
    page.getUsernameInput().sendKeys('1234');
    page.getPasswordInput().sendKeys('password');
    page.getLoginButton().click();
    expect(page.getFailedLoginResponse()).toContain('Invalid username and/or password');
  });
});

function logIn(username, password, thePage){
  thePage.getUsernameInput().sendKeys(username);
  thePage.getPasswordInput().sendKeys(password);
  thePage.getLoginButton().click();
};

describe('Login in with proper credentials', () => {

  beforeAll(() => {
    page = new LoginPage();
    testConfig = new TestConfig();
    baseURL = testConfig.getBaseURL();
    page.navigateTo();
  });

  it('should be able to login in with admin credentials and reach the admin page', () => {
    logIn(AdminUsername, AdminPassword, page);
    expect(page.getCurrentUrl()).toEqual(baseURL + 'app-home');
  });

  xit('should be able to login in with associate credentials and reach the associate page', () => {
    logIn(associateUsername, associatePassword, page);
    expect(page.getCurrentUrl()).toEqual(baseURL + 'associate-view');
  });

  it('should be able to login in with Manager credentials and reach the Admin page', () => {
    logIn(stagingManagerUsername, stagingManagerPassword, page);
    expect(page.getCurrentUrl()).toEqual(baseURL + 'app-home');
  });

  it('should be able to login in with Trainer credentials and reach the Trainer page', () => {
    logIn(trainerUsername, trainerPassword, page);
    expect(page.getCurrentUrl()).toEqual(baseURL + 'trainer-view');
  });

  it('should be able to login in with Sales Team credentials and reach the Admin page', () => {
    logIn(deliverySalesUsername, deliverySalesPassword, page);
    expect(page.getCurrentUrl()).toEqual(baseURL + 'app-home');
  });
  afterEach(() => {
    page.getlogoutButton().click();
  });
});
