import { Navbar } from './navbar.po';
import { TestConfig } from '../configuration/test-config';

describe('navbar functionality', () => {
    let navbar          : Navbar;
    let testConfig      : TestConfig;
    let baseURL         : string;

    beforeAll(() => {
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to home page', () => {
        navbar.goToHome();
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
});
