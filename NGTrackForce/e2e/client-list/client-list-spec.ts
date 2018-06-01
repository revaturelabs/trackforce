import { ClientListPo } from "./client-list.po";
import { TestConfig } from "../configuration/test-config";

describe('When navigating to the client-list page it', function() {

    let clientList         : ClientListPo;
    let testConfig         : TestConfig;
    let baseURL            : string;
    let testURL            : string;
    let searchByClientName : string;

    beforeAll(() => {
        clientList = new ClientListPo();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        testURL = 'client-listing';
        searchByClientName = 'FINRA';
    });

    it('should navigate to the client-list page', () => {
        clientList.navigateTo();
        expect(clientList.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        clientList.inputClientName(searchByClientName); 
    });
    
    it('should yield match for search input in one exists', () => {
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    it('should yield a match for a search regardless of case', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName(searchByClientName.toLocaleLowerCase());
        clientList.clientListSpan.getText()
        .then(text => {
            expect(text).toEqual(searchByClientName);
        });
    });

    // it('should yield a match for a search string with spaces', () => {
    //     clientList.clientSearch.clear();
    //     let searchInput = '3 s business corporation inc'
    //     clientList.inputClientName(searchInput);
    //     clientList.clientListSpan.getText()
    //     .then(text => {
    //             console.log(text);
    //             expect(text).toEqual('3 S Business Corporation Inc (BlackListed)');
    //         });  
    //     });

    // it('should trim the spaces between words in a search string', () => {
    //     clientList.clientSearch.clear();
    //     let searchInput = '3 s        business       corporation inc';
    //     clientList.inputClientName(searchInput);
    //     clientList.clientListSpan.getText()
    //     .then(text => {
    //         console.log('Empty text???' + text);
    //         // expect(text).toEqual('');
    //     });  
    // });

    it('should yield a match for search input if many results exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName('Accenture');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toEqual(4);
        });
    });

    it('should yield no matches if none exist', () => {
        clientList.clientSearch.clear();
        clientList.inputClientName('Some Nonsense');
        clientList.getClientResultListCount()
        .then(count => {
            expect(count).toEqual(0);
        });
    });

    it ('should logout', function() {
        clientList.logout();
    });
});
