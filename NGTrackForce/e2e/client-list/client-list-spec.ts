import { ClientListPo } from "./client-list.po";
import { Navbar } from "../navbar/navbar.po";
import { LoginPage } from '../login/login.po';
import { TestConfig } from "../configuration/test-config";
import { by, element } from 'protractor';

let clientList  : ClientListPo;
let testConfig  : TestConfig;
let navbar      : Navbar;
let page        : LoginPage;
let baseURL     : string;
let testURL     : string;
let searchByClientName: string;

describe('When an admin navigates to the client-list page it', function() {

    beforeAll(() => {
        clientList = new ClientListPo();
        testConfig = new TestConfig();
        page = new LoginPage();
        navbar = new Navbar();
        baseURL = testConfig.getBaseURL();
        testURL = 'client-listing';
        searchByClientName = 'Accenture';
        page.navigateTo();
        navbar.logIn("TestAdmin","TestAdmin");
    });

    it('should navigate to the client-list page', () => {
        navbar.goToClientList();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toContain(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    it('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = 'AMG Technology'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toContain('AMG Technology');
            });
    });

    it('should yield a match for search input if many results exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Infosys');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toBeGreaterThan(1);
        });
    });

    it('should yield no matches if none exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Some Nonsense');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toEqual(0);
        });
    });

    afterAll( () => {
        page.getlogoutButton().click();
    });
});

describe('When an Staging Manager navigates to the client-list page it', function() {

    beforeAll(() => {
      clientList = new ClientListPo();
      testConfig = new TestConfig();
      page = new LoginPage();
      navbar = new Navbar();
      baseURL = testConfig.getBaseURL();
      testURL = 'client-listing';
      searchByClientName = 'Accenture';
      page.navigateTo();
      navbar.logIn("bobstage","bobstage");
    });

    it('should navigate to the client-list page', () => {
        navbar.goToClientList();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input if one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toContain(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    it('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = '22nd Century Staffing Inc'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toEqual('22nd Century Staffing Inc');
            });
    });

    it('should yield a match for search input if many results exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Infosys');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toBeGreaterThan(1);
        });
    });

    it('should yield no matches if none exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Some Nonsense');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toEqual(0);
        });
    });

    afterAll( () => {
        page.getlogoutButton().click();
    });
});

describe('When an Delivery/Sales navigates to the client-list page it', function() {

    beforeAll(() => {
      clientList = new ClientListPo();
      testConfig = new TestConfig();
      page = new LoginPage();
      navbar = new Navbar();
      baseURL = testConfig.getBaseURL();
      testURL = 'client-listing';
      searchByClientName = 'Accenture';
      page.navigateTo();
      navbar.logIn("salestest","salestest");
    });

    it('should navigate to the client-list page', () => {
        navbar.goToClientList();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toContain(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toContain(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    it('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s business corporation inc'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toEqual('3 S Business Corporation Inc (BlackListed)');
            });
    });

    it('should yield a match for search input if many results exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Infosys');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toBeGreaterThan(1);
        });
    });

    it('should yield no matches if none exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys('Some Nonsense');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toEqual(0);
        });
    });

    afterAll( () => {
        page.getlogoutButton().click();
    });
});
