import { ClientListPo } from "./client-list.po";
import { TestConfig } from "../configuration/test-config";

describe('client-list page input and button fields', function() {

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
       // clientList.getClientNameValue
        expect(clientList.getClientNameValue).toEqual(searchByClientName);
    });

    /*it('should click view all data', ()=>{
        clientList.clickGetAllUsers();
        expect(clientList.)
    })*/
    // it('should show the correct bar chart header', () => {
    //     clientList.clickGetAllUsers();
    //     expect(clientList.checkBarChartHeader() == true);
    // });
});
