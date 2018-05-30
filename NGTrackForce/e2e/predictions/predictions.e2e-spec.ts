// import { PredictionsPage } from "./predictions.po";
import { Navbar } from "../navbar/navbar.po";
import { TestConfig } from "../configuration/test-config";

describe('predictions page functionality', () => {
    // let predictionsPage: PredictionsPage;
    let navbar: Navbar;
    let testConfig: TestConfig;
    let baseURL: string;

    beforeAll(() => {
        // predictionsPage = new PredictionsPage();
        navbar = new Navbar();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
    });

    it('should navigate to the predictions page', () => {
        navbar.goToPredictions();
        expect(navbar.getCurrentURL()).toEqual(baseURL + 'predictions');
    });
});