describe('Lets test searching on associates list tab...', function () {

    //Tested on local host, should go to EC2 Location
    browser.get('http://localhost:4200/login');

    //NGTrackForce_URL
    // browser.get('http://34.227.178.103:8090/NGTrackForce/');
    browser.manage().timeouts().implicitlyWait(5000);

    it('should log in', function() {
  
        element(by.id('username')).sendKeys('TestAdmin');
        element(by.id('password')).sendKeys('TestAdmin');
        element(by.xpath("//button[@type='submit']")).click();
        expect(element(by.id('home')).getText()).toEqual('Home');
        
    });
  
    it('Go to associate list tab', function() {
      
        element(by.id('associates')).click();
        expect(element(by.id('tableScroller')).isPresent()).toBe(true);
        
    });

    it('Test out search by name of associate', function() {
        associate = "Steven";
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

    it('Test out search by last name',function() {
        associateLast = "Huelsman";
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
