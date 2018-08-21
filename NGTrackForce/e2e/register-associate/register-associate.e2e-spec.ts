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
    });

    it('allow user to click the register button', () => {
        registerAssociatePage.clickRegisterBtn();
    });

    it('allow associate to enter info into input fields', () => {
        registerAssociatePage.enterAssociateInfo('john', 'john');
    });

    xit('should allow associate to click the next page button', () => {
        registerAssociatePage.clickNextPageBtn();
    });

    it('should allow associate to enter first and last name', () => {
        registerAssociatePage.enterFirstName('John');
        registerAssociatePage.enterLastName('Doe');
    });

    it('should allow associate to register as an associate by clicking register button', () => {
        registerAssociatePage.clickRegisterBtn();
    });

    it('exit browser', () => {
        loginPage.navigateTo();
    });

});
