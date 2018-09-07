import { RegisterAssociatePage } from "./register-associate.po";
import { TestConfig } from "../configuration/test-config";
import { LoginPage } from "../login/login.po";
import { browser } from "protractor";


describe('A new user should able to register', () => {
    let registerAssociatePage: RegisterAssociatePage;
    let loginPage            : LoginPage
    let testConfig           : TestConfig;
    let baseURL              : string;

    beforeAll(() => {
        registerAssociatePage = new RegisterAssociatePage();
        loginPage = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the login page', () => {
        loginPage.navigateTo();
        expect(registerAssociatePage.RegisterBtn()).toBeTruthy();
    });

    it('allow user to click the register button', () => {
        registerAssociatePage.RegisterBtn().click();
        expect(registerAssociatePage.RegisterUserBtn()).toBeTruthy();
    });

    it('allow associate to enter a username', () => {
        registerAssociatePage.AssociateUsername().sendKeys('jimbob');
        expect(registerAssociatePage.AssociateUsername().getAttribute("value")).toEqual('jimbob');
    });

    it('allow associate to enter a valid password', () => {
        registerAssociatePage.AssociatePassword().sendKeys('Testca$e1');
        expect(registerAssociatePage.AssociatePassword().getAttribute("value")).toEqual('Testca$e1');
    });

    it('allow associate to re-enter a valid password', () => {
        registerAssociatePage.AssociateCPassword().sendKeys('Testca$e1');
        expect(registerAssociatePage.AssociateCPassword().getAttribute("value")).toEqual('Testca$e1');
    });

    /*@Jacob Golding
      this would test a function no loger a part of the project
    */
    // xit('should allow associate to click the next page button', () => {
    //     registerAssociatePage.clickNextPageBtn();
    // });

    it('should allow associate to enter first name', () => {
        registerAssociatePage.FirstName().sendKeys('John');
        expect(registerAssociatePage.FirstName().getAttribute("value")).toEqual('John')
    });

    it('should allow associate to enter last name', () => {
        registerAssociatePage.LastName().sendKeys('Doe');
        expect(registerAssociatePage.LastName().getAttribute("value")).toEqual('Doe')
    });

    it('show allow associate to pick a role', () => {
      registerAssociatePage.RolePick().click();
      expect(registerAssociatePage.RolePick().getText()).toEqual('Associate');
    })

    it('should allow the user to register as an associate', () => {
        registerAssociatePage.RegisterUserBtn().click();
        expect(registerAssociatePage.DangerAlert().getText()).toContain('Associate account creation successful.');
    });
    afterAll(() => {
      loginPage.navigateTo();
    })
});

describe('A new user should not be able to register with a short username', () => {
    let registerAssociatePage: RegisterAssociatePage;
    let loginPage            : LoginPage
    let testConfig           : TestConfig;
    let baseURL              : string;

    beforeAll(() => {
        registerAssociatePage = new RegisterAssociatePage();
        loginPage = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        loginPage.navigateTo();
        registerAssociatePage.RegisterBtn().click();
        registerAssociatePage.AssociatePassword().sendKeys('Testca$e1');
        registerAssociatePage.AssociateCPassword().sendKeys('Testca$e1');
        registerAssociatePage.FirstName().sendKeys('John');
        registerAssociatePage.LastName().sendKeys('Doe');
        registerAssociatePage.RolePick().click();
    });

    it('allow associate to enter an invalid username', () => {
        registerAssociatePage.AssociateUsername().sendKeys('jim');
        expect(registerAssociatePage.AssociateUsername().getAttribute("value")).toEqual('jim');
    });

    it('should not allow the user to register with an invalid username', () => {
        registerAssociatePage.RegisterUserBtn().click();
        expect(registerAssociatePage.DangerAlert().getText()).toContain('Invalid username!');
    });

    afterAll(() => {
      loginPage.navigateTo();
    })
});
describe('A new user should not be able to register with a weak password', () => {
    let registerAssociatePage: RegisterAssociatePage;
    let loginPage            : LoginPage
    let testConfig           : TestConfig;
    let baseURL              : string;

    beforeAll(() => {
        registerAssociatePage = new RegisterAssociatePage();
        loginPage = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        loginPage.navigateTo();
        registerAssociatePage.RegisterBtn().click();
        registerAssociatePage.AssociateUsername().sendKeys('jimbob');
        registerAssociatePage.FirstName().sendKeys('John');
        registerAssociatePage.LastName().sendKeys('Doe');
        registerAssociatePage.RolePick().click();
    });

    it('allow associate to enter an invalid password', () => {
        registerAssociatePage.AssociatePassword().sendKeys('password');
        expect(registerAssociatePage.AssociatePassword().getAttribute("value")).toEqual('password');
    });

    it('allow associate to re-enter an invalid password', () => {
        registerAssociatePage.AssociateCPassword().sendKeys('password');
        expect(registerAssociatePage.AssociateCPassword().getAttribute("value")).toEqual('password');
    });

    it('should not allow the user to register with an invalid password', () => {
        registerAssociatePage.RegisterUserBtn().click();
        expect(registerAssociatePage.DangerAlert().getText()).toContain('Invalid password!');
    });

    afterAll(() => {
      loginPage.navigateTo();
    })
});

describe('A new user should not be able to register with a mismatched paswords', () => {
    let registerAssociatePage: RegisterAssociatePage;
    let loginPage            : LoginPage
    let testConfig           : TestConfig;
    let baseURL              : string;

    beforeAll(() => {
        registerAssociatePage = new RegisterAssociatePage();
        loginPage = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        loginPage.navigateTo();
        registerAssociatePage.RegisterBtn().click();
        registerAssociatePage.AssociateUsername().sendKeys('jimbob');
        registerAssociatePage.AssociatePassword().sendKeys('Testca$e1');
        registerAssociatePage.FirstName().sendKeys('John');
        registerAssociatePage.LastName().sendKeys('Doe');
        registerAssociatePage.RolePick().click();
    });

    it('allow associate to enter an different password', () => {
      registerAssociatePage.AssociateCPassword().sendKeys('Testca$e2');
      expect(registerAssociatePage.AssociateCPassword().getAttribute("value")).toEqual('Testca$e2');
    });

    it('should not allow the user to register with an invalid username', () => {
        registerAssociatePage.RegisterUserBtn().click();
        expect(registerAssociatePage.DangerAlert().getText()).toContain('Passwords do not match!');
    });

    afterAll(() => {
      loginPage.navigateTo();
    })
});

describe('A new user should not be able to register with a mismatched paswords', () => {
    let registerAssociatePage: RegisterAssociatePage;
    let loginPage            : LoginPage
    let testConfig           : TestConfig;
    let baseURL              : string;

    beforeAll(() => {
        registerAssociatePage = new RegisterAssociatePage();
        loginPage = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        loginPage.navigateTo();
        registerAssociatePage.RegisterBtn().click();
        registerAssociatePage.AssociateUsername().sendKeys('jimbob');
        registerAssociatePage.AssociatePassword().sendKeys('Testca$e1');
        registerAssociatePage.AssociateCPassword().sendKeys('Testca$e1');
        registerAssociatePage.FirstName().sendKeys('John');
        registerAssociatePage.LastName().sendKeys('Doe');
    });

    it('should not allow the user to register without selecting a role ', () => {
        registerAssociatePage.RegisterUserBtn().click();
        expect(registerAssociatePage.DangerAlert().getText()).toContain('Please select a role!');
    });

    afterAll(() => {
      loginPage.navigateTo();
    })
});
