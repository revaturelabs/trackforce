import { Navbar } from './navbar.po';
import { LoginPage } from '../login/login.po';
import { TestConfig } from '../configuration/test-config';

let navbar          : Navbar;
let page            : LoginPage;
let testConfig      : TestConfig;
let baseURL         : string;

describe('The admin navbar is functional', () => {

    beforeAll(() => {
        navbar = new Navbar();
        page = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn("TestAdmin","TestAdmin");
    });

    it('should navigate to home page', () => {
        navbar.goToAdminHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'app-home');
    });

    it('should navigate to client list page', () => {
        navbar.goToClientList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'client-listing');
    });

    it('should navigate to batch list page', () => {
        navbar.goToBatchList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'batch-listing');
    });

    it('should navigate to associate lising', () => {
        navbar.goToAssociateList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'associate-listing');
    });

    it('should navigate to predictions', () => {
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should navigate to create user', () => {
        navbar.goToCreateUser();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'create-user');
    });

    it('should navigate to salesforce', () => {
      navbar.goToSalesForce();
      expect(navbar.getCurrentURL()).toEqual(baseURL + 'salesforce')
    });

    it('should be able to logout', () => {
        page.getlogoutButton().click();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'login');
    });

});

describe('The Asscoiate navbar is functional', () => {

    beforeAll(() => {
        navbar = new Navbar();
        page = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn("cyril","cyril");
    });

    it('should navigate to home page', () => {
        navbar.goToAssociateHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'associate-view');
    });
    /* @Jacob Golding
      This test will fail until the bug on the My Interview page where it
      switch back to the home page is fixed
    */
    it('should navigate to My Interviews', () => {
        navbar.goToMyInterview();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'myinterview-view');
    });

    it('should be able to logout', () => {
        page.getlogoutButton().click();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'login');
    });
});

describe('The Staging Manager navbar is functional', () => {

    beforeAll(() => {
        navbar = new Navbar();
        page = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn("bobstage","bobstage");
    });

    it('should navigate to home page', () => {
        navbar.goToAdminHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'app-home');
    });

    it('should navigate to client list page', () => {
        navbar.goToClientList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'client-listing');
    });

    it('should navigate to batch list page', () => {
        navbar.goToBatchList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'batch-listing');
    });

    it('should navigate to associate lising', () => {
        navbar.goToAssociateList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'associate-listing');
    });

    it('should navigate to predictions', () => {
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should navigate to create user', () => {
        navbar.goToCreateUser();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'create-user');
    });

    it('should be able to logout', () => {
        page.getlogoutButton().click();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'login');
    });

});
/* @Jacob Golding
  This test will fail because of a bug on the trainer where the navbar does not load
  on the initial load of the web page, also do not uncomment out these tests until
  trainer page is fixed
*/
describe('The Trainer navbar is functional', () => {

    beforeAll(() => {
        navbar = new Navbar();
        page = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn("Trainer0","Trainer");
    });

    it('should navigate to home page', () => {
        navbar.goToTrainerHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'trainer-view');
    });

    it('should navigate to batch list page', () => {
        navbar.goToBatchList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'batch-listing');
    });

    it('should navigate to associate lising', () => {
        navbar.goToAssociateList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'associate-listing');
    });

    it('should be able to logout', () => {
        page.getlogoutButton().click();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'login');
    });

});

describe('The Delivery/Sales navbar is functional', () => {

    beforeAll(() => {
        navbar = new Navbar();
        page = new LoginPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        page.navigateTo();
        navbar.logIn("salestest","salestest");
    });

    it('should navigate to home page', () => {
        navbar.goToAdminHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'app-home');
    });

    it('should navigate to client list page', () => {
        navbar.goToClientList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'client-listing');
    });

    it('should navigate to batch list page', () => {
        navbar.goToBatchList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'batch-listing');
    });

    it('should navigate to associate lising', () => {
        navbar.goToAssociateList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'associate-listing');
    });

    it('should navigate to predictions', () => {
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should navigate to create user', () => {
        navbar.goToCreateUser();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'create-user');
    });

    it('should be able to logout', () => {
        page.getlogoutButton().click();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'login');
    });

});
