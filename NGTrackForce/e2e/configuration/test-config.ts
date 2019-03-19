import { ngEnvironment } from '../../src/environments/environment';
export class TestConfig{
    private baseURL: string;

    /**
     * Stores the base URL used by Jasmine for testing
     * Switch this depending with you are working in a localhost
     * environment or on the pipeline
     * you may or may not need a #/ appended
     */
    constructor() {
      this.baseURL = ngEnvironment.url +'#/';
      //this.baseURL = ngEnvironment.url;
     //this.baseURL = 'http://ec2-34-227-178-103.compute-1.amazonaws.com:8090/NGTrackForce/';
    }

    /**
     * Returns the base URL used by Jasmine for testing
     */
    getBaseURL(){
        return this.baseURL;
    }
}
