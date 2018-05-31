import { PredictionsPage } from "./predictions.po";
import { Navbar } from "../navbar/navbar.po";
import { TestConfig } from "../configuration/test-config";

// Happy Path
describe('Successfully input valid data into predictions filter', () => {
    let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
        predictionsPage = new PredictionsPage();
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow user to input valid data', () => {
        predictionsPage.inputStartDate('03042016');
        predictionsPage.inputEndDate('04062018');
        predictionsPage.inputNumAssociates('20');
        predictionsPage.filterByTechnologies();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy;
    });

    it('should provide a table with predictions', () => {
        expect(predictionsPage.getPredictionsTableHead()).toBeTruthy;
    });

    it('should provide the same number of rows entered into the filter', () => {
        expect(predictionsPage.getPredictionsTableEntriesCount()).toEqual(3);
    });

    it('should provide details when the details button is pressed', () => {
        predictionsPage.detailsBtn.click();
        expect(predictionsPage.getPredictionsBreakdownHeader).toBeTruthy();
    });
});

describe('Successfully input valid data using all filters', () => {
    let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
        predictionsPage = new PredictionsPage();
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
        navbar.goToClientList();
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow user to input valid data and use all filters', () => {
        predictionsPage.inputStartDate('03042016');
        predictionsPage.inputEndDate('04062018');
        predictionsPage.inputNumAssociates('5');
        predictionsPage.checkFirstNineBoxes();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy;
    });

    it('should provide the same number of rows entered into the filter', () => {
        expect(predictionsPage.getPredictionsTableEntriesCount()).toEqual(10);
    });

    it('should provide details when the details button is pressed', () => {
        predictionsPage.detailsBtn.click();
        expect(predictionsPage.getPredictionsBreakdownHeader).toBeTruthy();
    });
});

describe('A start date that is more current than the end data returns 0 associates', () => {
    let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
        predictionsPage = new PredictionsPage();
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
        navbar.goToClientList();
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow user to input a start date that is more current than the end date', () => {
        predictionsPage.inputStartDate('03042018');
        predictionsPage.inputEndDate('04062016');
        predictionsPage.inputNumAssociates('30');
        predictionsPage.filterByTechnologies();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy;
    });

    it('should yield 0 associates', () => {
        let numAssociates = predictionsPage.numberOfAssociatesInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('0');
        });
    });

    it('should have a negative amount in Difference', () => {
        let difference = predictionsPage.differenceInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('-30');
        });
    });
});

describe('The number of associates input can handle a very large numeric input', () => {
    let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
        predictionsPage = new PredictionsPage();
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
        navbar.goToClientList();
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow user to input a very large number', () => {
        predictionsPage.inputStartDate('03042016');
        predictionsPage.inputEndDate('04062018');
        predictionsPage.inputNumAssociates('1000000000000000000000000000000000000000000000000000000000');
        predictionsPage.filterByTechnologies();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy;
    });

    it('should yield some associates', () => {
        let numAssociates = predictionsPage.numberOfAssociatesInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('38');
        });
    });

    it('should have a negative amount in Difference', () => {
        let difference = predictionsPage.differenceInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('-1e+57');
        });
    });
});
