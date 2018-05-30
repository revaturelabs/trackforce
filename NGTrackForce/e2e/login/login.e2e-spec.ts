import { LoginPage } from './login.po';
/*
Smoke test: Checks elements to be exist
*/
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
    expect(page.getFailedLoginResponse()).toEqual('Please enter a username and password');
  });

  it('should fail to login when incorrect credentials are entered', () => {
    page.getUsernameInput().sendKeys('1234');
    page.getPasswordInput().sendKeys('password');
    page.getLoginButton().click();
    expect(page.getFailedLoginResponse()).toEqual('Invalid username and/or password');
  });

});

describe('confirm login navigation', () => {
  let page: LoginPage;

  beforeAll(() => {
    page = new LoginPage();
    page.navigateTo();
  });

  it('should reach the index page', () => {
    page.getUsernameInput().sendKeys('TestAdmin');
    page.getPasswordInput().sendKeys('TestAdmin');
    page.getLoginButton().click();

    expect(page.getTitle()).not.toEqual('NGTrackForce');
  });
});
