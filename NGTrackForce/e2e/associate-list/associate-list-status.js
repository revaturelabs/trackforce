describe('Testing associate list page further by changing search status.', function() {
    //Tested on local host, should go to EC2 Location
    //browser.get('http://localhost:4200/login');

    //NGTrackForce_URL
    browser.get('http://34.227.178.103:8090/NGTrackForce/');
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

    it('Select mapped: training', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[2]')).click();
    });

    it('Select mapped: reserved', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[3]')).click();
    });

    it('Select MAPPED: SELECTED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[4]')).click();
    });

    it('Select MAPPED: CONFIRMED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[5]')).click();
    });

    it('Select MAPPED: DEPLOYED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[6]')).click();
    });

    it('Select UNMAPPED: TRAINING', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[7]')).click();
    });

    it('Select UNMAPPED: OPEN', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[8]')).click();
    });

    it('Select UNMAPPED: SELECTED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[9]')).click();
    });


    it('Select UNMAPPED: CONFIRMED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[10]')).click();
    });


    it('Select UNMAPPED: DEPLOYED', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[11]')).click();
    });

    
    it('Select default', function() {
        element(by.id('exampleFormControlSelect2')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect2"]/option[1]')).click();
        //logout
        element(by.id('navbarDropdown')).click();
        element(by.id('logout')).click();
    });
    
});