import { PredictionsPage } from "./predictions.po";
import { Navbar } from "../navbar/navbar.po";
import { TestConfig } from "../configuration/test-config";
import { LoginPage } from "../login/login.po";
import { browser, element, by } from "protractor";

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
      browser.driver.manage().window().maximize();
    });

    it('should navigate to the predictions page', () => {
      loginPage.navigateTo();
      navbar.logIn('TestAdmin','TestAdmin');
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
      predictionsPage.inputTechCount(0, 4);
      predictionsPage.clickCheckResourcesButton();
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

    //This test seems as if it's attempting to do the same thing as one of the above tests, so I've commented it out
    //for the time being.
    /*it('should provide the same number of rows entered into the filter', () => {

      expect(predictionsPage.getPredictionsTableRows()).toEqual(predictionsPage.getTechnologyCount());
    });*/

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
        expect(predictionsPage.getPredictionsTable()).toBeTruthy();
    });

    //adding this after all breaks the last test of this suite, to get around this
    //we simply put the jasmine tests first.

    // afterAll(()=>{
    //   element(by.id('logout')).click();
    // });

  });
