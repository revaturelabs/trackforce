export class TestConfig{
    private baseURL     : string;

    /**
     * Stores the base URL used by Jasmine for testing
     */
    constructor() {
<<<<<<< HEAD
        this.baseURL = 'http://52.207.66.231:4200';
=======
        this.baseURL = 'http://localhost:4200/';
		//this.baseURL = ' http://52.207.66.231:4200/';
>>>>>>> 28b339fece17ae392b80837d963dc8c114774760
    }

    /**
     * Returns the base URL used by Jasmine for testing
     */
    getBaseURL(){
        return this.baseURL;
    }
}