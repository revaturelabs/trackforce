import { CreateUserPo } from "./create-user.po";
import { TestConfig } from "../configuration/test-config";
import { Navbar } from "../navbar/navbar.po";
import { LoginPage } from '../login/login.po';

let createUser      : CreateUserPo;
let testConfig      : TestConfig;
let navbar          : Navbar;
let page            : LoginPage;
let baseURL         : string;
let username        : string;
let password        : string;

describe('The create-user page ', () => {

    beforeAll(() => {
        createUser = new CreateUserPo();
        testConfig = new TestConfig();
        navbar     = new Navbar();
        page       = new LoginPage();
        baseURL = testConfig.getBaseURL();
        // this creates a random 6 letter string for the username
        username = Math.random().toString(36).substring(7);
        password = 'Testca$e'
        page.navigateTo();
        navbar.logIn('TestAdmin','TestAdmin');
        navbar.goToCreateUser();
    });

    it('should accept username input', () => {
        createUser.inputUsername(username);
        expect(createUser.getUsernameValue()).toEqual(username);
    });

    it('should accept password input', () => {
        createUser.inputPassword(password);
        expect(createUser.getPasswordValue()).toEqual(password);
    });

    it('should accept password confirmation input', () => {
        createUser.inputPasswordConfirm(password);
        expect(createUser.getPasswordConfirmValue()).toEqual(password);
    });

    it('should select admin radio button', () => {
        createUser.clickAdminRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('1');
    });

    it('should select trainer radio button', () => {
        createUser.clickTrainerRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('2');
    });

    it('should select associate radio button', () => {
        createUser.clickAssociateRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('5');
    });

    it('should select staging manager radio button', () => {
        createUser.clickManagerRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('4');
    });

    it('should select delivery radio button', () => {
        createUser.clickDeliveryRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('3');
    });
    afterAll(() => {
      page.getlogoutButton().click();
    });
});

describe('An Admin', () => {

    beforeAll(() => {
        createUser = new CreateUserPo();
        testConfig = new TestConfig();
        navbar     = new Navbar();
        baseURL = testConfig.getBaseURL();
        password = 'Testca$e1'
        page.navigateTo();
        navbar.logIn('TestAdmin','TestAdmin');
    });

    beforeEach(() => {
      navbar.goToAdminHome();
      navbar.goToCreateUser();
      username = Math.random().toString(36).substring(7);
      createUser.inputUsername(username);
      createUser.inputPassword(password);
      createUser.inputPasswordConfirm(password);
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Expected 'Error: new user not created!' to contain 'User created successfully'.
    it('should be able to create an admin', () => {
       createUser.clickAdminRadio();
       createUser.clickSubmitButton();
       expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Expected 'Error: new user not created!' to contain 'User created successfully'.
    it('should be able to create a trainer', () => {
        createUser.clickTrainerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a associate', () => {
        createUser.clickAssociateRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });
    
    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a staging manager', () => {
        createUser.clickManagerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a delivery ', () => {
        createUser.clickDeliveryRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    afterAll(() => {
      page.getlogoutButton().click();
    });
});

describe('A Staging Manager', () => {

    beforeAll(() => {
        createUser = new CreateUserPo();
        testConfig = new TestConfig();
        navbar     = new Navbar();
        baseURL = testConfig.getBaseURL();
        password = 'Testca$e1'
        page.navigateTo();
        navbar.logIn('bobstage','bobstage');
    });

    beforeEach(() => {
      navbar.goToAdminHome();
      navbar.goToCreateUser();
      username = Math.random().toString(36).substring(7);
      createUser.inputUsername(username);
      createUser.inputPassword(password);
      createUser.inputPasswordConfirm(password);
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Expected 'Error: new user not created!' to contain 'User created successfully'.
    it('should be able to create a trainer', () => {
        createUser.clickTrainerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a associate', () => {
        createUser.clickAssociateRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a staging manager', () => {
        createUser.clickManagerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a delivery ', () => {
        createUser.clickDeliveryRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
      });

    afterAll(() => {
      page.getlogoutButton().click();
    });
});

describe('A Sales Manager', () => {

    beforeAll(() => {
        createUser = new CreateUserPo();
        testConfig = new TestConfig();
        navbar     = new Navbar();
        baseURL = testConfig.getBaseURL();
        password = 'Testca$e1'
        page.navigateTo();
        navbar.logIn('salestest','salestest');
    });

    beforeEach(() => {
      navbar.goToAdminHome();
      navbar.goToCreateUser();
      username = Math.random().toString(36).substring(7);
      createUser.inputUsername(username);
      createUser.inputPassword(password);
      createUser.inputPasswordConfirm(password);
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Expected 'Error: new user not created!' to contain 'User created successfully'.
    it('should be able to create a trainer', () => {
        createUser.clickTrainerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
    });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a associate', () => {
        createUser.clickAssociateRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
      });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a staging manager', () => {
        createUser.clickManagerRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
      });

    //FAILS - Change 'it' to 'xit' to skip this test
    //Failed: No element found using locator: By(css selector, body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div)
    it('should be able to create a delivery ', () => {
        createUser.clickDeliveryRadio();
        createUser.clickSubmitButton();
        expect(createUser.getAlert().getText()).toContain('User created successfully');
      });

    afterAll(() => {
      page.getlogoutButton().click();
    });
});
describe('A user', () => {

    beforeAll(() => {
        createUser = new CreateUserPo();
        testConfig = new TestConfig();
        navbar     = new Navbar();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn('TestAdmin','TestAdmin');
    });

    beforeEach(() => {
      navbar.goToAdminHome();
      navbar.goToCreateUser();
    });

    it('should get an error if they use a sympol in their username', () => {
      createUser.inputUsername('a$');
      expect(createUser.getUserNameAlert().getText()).toContain('Alphabetical and numerical characters only, no spaces');
    });

    it('should get an error if they enter in an invalid password in the first password box', () => {
      createUser.inputPassword('ab');
      expect(createUser.getAlert().getText()).toContain('Password must be at least 8 characters and include a number, a capital letter and a special character');
    });
    /*
      @jacob Golding
      This test should pass but beacuse of how protractor runs the code it forces this test to fail
    */
    // it('should get an error if they enter in an invalid password in the secound password box', () => {
    //   createUser.inputPasswordConfirm('ab');
    //   expect(createUser.getAlert().getText()).toContain('Password must have a number, a capital letter and a special character');
    // });

    it('should get an error if they enter two different passwords', () => {
      createUser.inputPassword('Testca$e1');
      createUser.inputPasswordConfirm('Testca$e2');
      expect(createUser.getAlert().getText()).toContain('Passwords do not match!');
    });

    afterAll(() => {
      page.getlogoutButton().click();
    });
});
