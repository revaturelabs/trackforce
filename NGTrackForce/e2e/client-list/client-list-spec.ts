import { ClientListPo } from "./client-list.po";
import { TestConfig } from "../configuration/test-config";
import { by, element } from 'protractor';

describe('When an admin navigates to the client-list page it', function() {

    let clientList: ClientListPo;
    let testConfig: TestConfig;
    let baseURL: string;
    let testURL: string;
    let searchByClientName: string;

    beforeAll(() => {
        clientList = new ClientListPo();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        testURL = 'client-listing';
        searchByClientName = 'Accenture';
        clientList.startUp();
        clientList.logIn("TestAdmin","TestAdmin");
    });

    it('should navigate to the client-list page', () => {
        clientList.navigateTo();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    /* @Jacob Golding
      Cannot propely implement these test since in the test data there
      are no name with spaces in them
    */
    xit('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s business corporation inc'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toEqual('3 S Business Corporation Inc (BlackListed)');
            });
        });

    xit('should trim the spaces between words in a search string', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s        business       corporation inc';
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual('');
        });
    });
    /* @Jacob Golding
      This should fail because multiple client of the same company have not been
      added but because of a bug this test may pass
    */
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

    it ('should logout', function() {
        clientList.logout();
    });
});

describe('When an Staging Manager navigates to the client-list page it', function() {

    let clientList: ClientListPo;
    let testConfig: TestConfig;
    let baseURL: string;
    let testURL: string;
    let searchByClientName: string;

    beforeAll(() => {
        clientList = new ClientListPo();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        testURL = 'client-listing';
        searchByClientName = 'Infosys';
        clientList.startUp();
        clientList.logIn("bobstage","bobstage");
    });

    it('should navigate to the client-list page', () => {
        clientList.navigateTo();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    /* @Jacob Golding
      Cannot propely implement these test since in the test data there
      are no name with spaces in them
    */
    xit('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s business corporation inc'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toEqual('3 S Business Corporation Inc (BlackListed)');
            });
        });

    xit('should trim the spaces between words in a search string', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s        business       corporation inc';
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual('');
        });
    });
    /* @Jacob Golding
      This should fail because multiple client of the same company have not been
      added but because of a bug this test may pass
    */
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

    it ('should logout', function() {
        clientList.logout();
    });
});

describe('When an Delivery/Sales navigates to the client-list page it', function() {

    let clientList: ClientListPo;
    let testConfig: TestConfig;
    let baseURL: string;
    let testURL: string;
    let searchByClientName: string;

    beforeAll(() => {
        clientList = new ClientListPo();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        testURL = 'client-listing';
        searchByClientName = 'Revature';
        clientList.startUp();
        clientList.logIn("salestest","salestest");
    });

    it('should navigate to the client-list page', () => {
        clientList.navigateTo();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName().sendKeys(searchByClientName);
        expect(clientList.inputClientName().getAttribute("value")).toEqual(searchByClientName);
    });

    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName().sendKeys(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should be able to click a searched for client', () => {
      clientList.clientListSpan.click();
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual(searchByClientName);
      });
    });

    it('should be able to click View Data for all Clients', () => {
      clientList.clickGetAllClientDataBtn()
      clientList.getBarChartHeader().getText().then(text => {
        expect(text).toEqual('All Client Data');
      });
    })

    /* @Jacob Golding
      Cannot propely implement these test since in the test data there
      are no name with spaces in them
    */
    xit('should yield a match for a search string with spaces', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s business corporation inc'
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
                expect(text).toEqual('3 S Business Corporation Inc (BlackListed)');
            });
        });

    xit('should trim the spaces between words in a search string', () => {
        clientList.clientSearch.clear();
        let searchInput = '3 s        business       corporation inc';
        clientList.inputClientName().sendKeys(searchInput);
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual('');
        });
    });
    /* @Jacob Golding
      This should fail because multiple client of the same company have not been
      added but because of a bug this test may pass
    */
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

    it('should have logout button', () => {
        let logout = clientList.getLogoutButton();
        let button = element(by.css('btn btn-danger'));
    })

    it ('should logout', function() {
        clientList.logout();
    });
});
