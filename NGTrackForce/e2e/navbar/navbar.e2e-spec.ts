import { Navbar } from './navbar.po';
import { TestConfig } from '../configuration/test-config';

xdescribe('navbar functionality', () => {
    let navbar          : Navbar;
    let testConfig      : TestConfig;
    let baseURL         : string;

    beforeAll(() => {
        navbar = new Navbar();
        navbar.navigateTo();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    })

    it('should navigate to home page', () => {
        navbar.goToHome();
        expect(navbar.getCurrentURL()).toEqual(baseURL + '/home');
    })

    it('should navigate to client list page', () => {
        navbar.goToClientList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + '/clientListing');
    })

    it('should navigate to batch list page', () => {
        navbar.goToBatchList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + '/batchListing');
    })

    it('should navigate to home page', () => {
        navbar.goToAssociateList();
        expect(navbar.getCurrentURL()).toEqual(baseURL + '/associateListing');
    })
});