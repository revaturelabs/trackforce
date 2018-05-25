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