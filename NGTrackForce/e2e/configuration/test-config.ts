export class TestConfig{
    private baseURL: string;

    /**
     * Stores the base URL used by Jasmine for testing
     * Switch this depending with you are working in a localhost
     * environment or or on the pipeline
     */
    constructor() {
      //this.baseURL = 'http://localhost:4200/#/';
      this.baseURL = 'http://34.227.178.103:8090/NGTrackForce/#/';
    }

    /**
     * Returns the base URL used by Jasmine for testing
     */
    getBaseURL(){
        return this.baseURL;
    }
}
