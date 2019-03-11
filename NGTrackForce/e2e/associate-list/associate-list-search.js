describe('tests searching on associates list tab...', function () {

    //Tested on local host, should go to EC2 Location
    browser.get('http://localhost:4200');

    //NGTrackForce_URL
    // browser.get('http://34.227.178.103:8090/NGTrackForce/');
    beforeEach(() =>{
        // browser.sleep(200);
        browser.manage().timeouts().implicitlyWait(5000);
    });

    it('should log in', function() {
  
        element(by.id('username')).sendKeys('TestAdmin');
        element(by.id('password')).sendKeys('TestAdmin');
        element(by.css('#pwd-container > div > section > form > div > div:nth-child(3) > button:nth-child(1)')).click();
        expect(element(by.id('home')).getText()).toEqual('Home');
        
    });
  
    it('Go to associate list tab', function() {
      
        element(by.id('associates')).click();
        expect(element(by.id('tableScroller')).isPresent()).toBe(true);
        
    });
    //this test will only pass if an associate with a field of John exists
    it('Test out search by name of associate', function() {
        associate = "John";
        element(by.id('FilterName')).sendKeys(associate);
        element(by.buttonText('Filter')).click();
        
        if(element(by.css("table tbody tr:nth-child(1) td:nth-child(1)")).isPresent){
            expect(element(by.css("table tbody tr:nth-child(1) td:nth-child(1)")).getText()).toEqual(associate);
        }
    });

    it('Clear the search input box', function() {
        element(by.buttonText('Clear Filters')).click();
        expect(by.id('FilterName')).toMatch('');
    });

    //this test will only function if someone of the last name Huelsman exists
    it('Test out search by last name',function() {
        associateLast = "Doe";
        element(by.id('FilterName')).sendKeys(associateLast);
        element(by.buttonText('Filter')).click();

        if(element(by.css("table tbody tr:nth-child(1) td:nth-child(2)")).isPresent){
            expect(element(by.css("table tbody tr:nth-child(1) td:nth-child(2)")).getText()).toEqual(associateLast);
        }
    });

    it('Clear out search input once more and logout', function(){
        element(by.buttonText('Clear Filters')).click();
        element(by.id('navbarDropdown')).click();
        element(by.id('logout')).click();
    });

});
