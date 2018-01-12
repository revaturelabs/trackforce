export class TestConfig{
    private baseURL     : string;

    /**
     * Stores the base URL used by Jasmine for testing
     */
    constructor() {
        this.baseURL = 'http://localhost:49155';
    }

    /**
     * Returns the base URL used by Jasmine for testing
     */
    getBaseURL(){
        return this.baseURL;
    }
}