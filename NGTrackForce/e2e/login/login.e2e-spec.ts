import { LoginPage } from './login.po';
import { browser } from 'protractor';

/*
Smoke test: Checks elements to be exist
*/
let login_urL = "http://localhost:4200/login";
let adminUrl = "http://localhost:4200/#/app-home";
let associateUrl = "http://localhost:4200/#/associate-view";
let trainerUrl = "http://localhost:4200/#/trainer-view";
let AdminUsername = "TestAdmin";
let AdminPassword = "TestAdmin";
let associateUsername = "cyril";
let associatePassword = "cyril";
let stagingManagerUsername = "bobstage";
let stagingManagerPassword = "bobstage";
let trainerUsername = "Trainer";
let trainerPassword = "Trainer";
let deliverySalesUsername = "salestest";
let deliverySalesPassword = "salestest";

describe('login page element existences', () => {
  let page: LoginPage;

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
    expect(page.getLoginButtonText()).toEqual('Sign in');
  });
});

describe('Confirm login failures', () => {
  let page: LoginPage;

  beforeAll(() => {
    page = new LoginPage();
    page.navigateTo();
  });

  it('should fail to log in when nothing is entered', () => {
    page.getLoginButton().click();
    expect(page.getFailedLoginResponse()).toEqual('Please enter a username and password\nUsername:\nPassword:\nSign in\nRegister');
  });

  it('should fail to login when incorrect credentials are entered', () => {
    page.getUsernameInput().sendKeys('1234');
    page.getPasswordInput().sendKeys('password');
    page.getLoginButton().click();
    expect(page.getFailedLoginResponse()).toEqual('Invalid username and/or password\nUsername:\nPassword:\nSign in\nRegister');
  });

});

describe('confirm login navigation', () => {
  let page: LoginPage;

  beforeAll(() => {
    page = new LoginPage();
    page.navigateTo();
  });

  it('should reach the index page', () => {
    page.getUsernameInput().sendKeys(AdminUsername);
    page.getPasswordInput().sendKeys(AdminPassword);
    page.getLoginButton().click();
    expect(page.getUrl()).toEqual(adminUrl);
  });
});
