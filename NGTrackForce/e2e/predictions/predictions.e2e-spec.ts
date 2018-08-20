import { PredictionsPage } from "./predictions.po";
import { Navbar } from "../navbar/navbar.po";
import { TestConfig } from "../configuration/test-config";
import { LoginPage } from "../login/login.po";
import { browser } from "../../node_modules/protractor";
import { forEach } from "../../node_modules/@angular/router/src/utils/collection";

// Happy Path
describe('When entering valid data into predictions filter it', () => {
    let predictionsPage: PredictionsPage;
    let loginPage: LoginPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
      predictionsPage = new PredictionsPage();
      loginPage = new LoginPage();
      navbar = new Navbar();
      testConfig = new TestConfig();
      baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
      loginPage.navigateTo();
      navbar.goToPredictions();
      expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow user to input valid data', () => {
      predictionsPage.inputStartDate('03042016');
      predictionsPage.inputEndDate('04062018');
      predictionsPage.inputTechCount(0, 20);
      predictionsPage.outOfFocus();
      expect(predictionsPage.getPredictionsTable()).toBeTruthy();
    });

    it('should provide a table with predictions', () => {
      expect(predictionsPage.getPredictionsTable()).toBeTruthy();
    });

    it('should provide the same number of rows entered into the filter', () => {
      expect(predictionsPage.getPredictionsTableRows()).toEqual(1);
    });

    it('should provide details when prediction row is clicked', () => {
        predictionsPage.clickPredictionRow(1);
        expect(predictionsPage.getPredictionDetailsHeader).toBeTruthy();
    });
});

describe('When entering valid data using all filters it', () => {
    let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
      predictionsPage = new PredictionsPage();
      navbar = new Navbar();
      testConfig = new TestConfig();
      baseURL = testConfig.getBaseURL();
      browser.waitForAngular();
    });

    it('should navigate to the predictions page', () => {
      navbar.goToClientList();
      navbar.goToPredictions();
      expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });

    it('should allow a user to input valid data and use all filters', () => {
      predictionsPage.inputStartDate('03042016');
      predictionsPage.inputEndDate('04062018');
      predictionsPage.getTechnologyCount().then(result => {
        for (let i = 0; i < result; ++i) {
          predictionsPage.inputTechCount(i, 10);
        }
        predictionsPage.outOfFocus();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy();
      });
    });

    it('should provide a table with predictions', () => {
      expect(predictionsPage.getPredictionsTable()).toBeTruthy();
    });

    it('should provide the same number of rows entered into the filter', () => {
      expect(predictionsPage.getPredictionsTableRows()).toEqual(predictionsPage.getTechnologyCount());
    });

    it('should provide details when prediction row is clicked', () => {
      predictionsPage.clickPredictionRow(1);
      expect(predictionsPage.getPredictionDetailsHeader()).toBeTruthy();
  });
});

describe('When I enter a start date that is more current than the end date it', () => {
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
        predictionsPage.inputTechCount(0, 30);
        predictionsPage.outOfFocus();
        expect(predictionsPage.getPredictionsTable()).toBeTruthy;
    });

    xit('should yield 0 associates', () => {
        let numAssociates = predictionsPage.numberOfAssociatesInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('0');
        });
    });

    xit('should have a negative amount in Difference', () => {
        let difference = predictionsPage.differenceInTableEntry.getText()
        .then(text => {
            expect(text).toEqual('-30');
        });
    });
});
