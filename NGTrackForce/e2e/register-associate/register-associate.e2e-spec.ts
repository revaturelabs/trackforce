import { RegisterAssociatePage } from "./register-associate.po";
import { TestConfig } from "../configuration/test-config";
import { LoginPage } from "../login/login.po";
import { browser } from "protractor";


describe('Registering as a new associate', () => {
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
        registerAssociatePage.AssociateUsername().sendKeys('john');
        expect(registerAssociatePage.AssociateUsername().getAttribute("value")).toEqual('john');
    });

    it('allow associate to enter a password', () => {
        registerAssociatePage.AssociatePassword().sendKeys('password');
        expect(registerAssociatePage.AssociatePassword().getAttribute("value")).toEqual('password');
    });

    it('allow associate to enter a password', () => {
        registerAssociatePage.AssociateCPassword().sendKeys('password');
        expect(registerAssociatePage.AssociateCPassword().getAttribute("value")).toEqual('password');
    });

    xit('should allow associate to click the next page button', () => {
        registerAssociatePage.clickNextPageBtn();
    });

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
      registerAssociatePage.RolePick().getAttribute("value")  .then(text => {
            console.log(text);
        });
    })

    // it('should allow associate to register as an associate by clicking register button', () => {
    //     registerAssociatePage.RegisterBtn();
    // });

    it('exit browser', () => {
        loginPage.navigateTo();
    });

});
