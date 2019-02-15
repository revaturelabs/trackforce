describe('Testing associate list page further by changing search status.', function() {
    //Tested on local host, should go to EC2 Location
    // browser.get('http://localhost:4200/login');

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
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[2]')).click();        
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Mapped: Training');
    });

    it('Select mapped: reserved', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[3]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Mapped: Reserved');
    });

    it('Select MAPPED: SELECTED', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[4]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Mapped: Selected');
    });

    it('Select MAPPED: CONFIRMED', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[5]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Mapped: Confirmed');
    });

    // For whatever reason, the 'Mapped: deployed' pairing was omitted from the dropdown menu
    // 
    // 
    // it('Select MAPPED: DEPLOYED', function() {
    //     element(by.id('stat')).click();
    //     element(by.xpath('//*[@id="stat"]/option[6]')).click();
    //     expect(element(by.id('stat')).getAttribute('value')).toEqual('Mapped: Deployed');
    // });

    it('Select UNMAPPED: TRAINING', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[6]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Unmapped: Training');
    });

    it('Select UNMAPPED: OPEN', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[7]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Unmapped: Open');
    });

    it('Select UNMAPPED: SELECTED', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[8]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Unmapped: Selected');
    });


    it('Select UNMAPPED: CONFIRMED', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[9]')).click();
        expect(element(by.id('stat')).getAttribute('value')).toEqual('Unmapped: Confirmed');
    });

//  This pair was also omitted for the dropdown menu
// 
    // it('Select UNMAPPED: DEPLOYED', function() {
    //     element(by.id('stat')).click();
    //     element(by.xpath('//*[@id="stat"]/option[10]')).click();
    //     expect(element(by.id('stat')).getAttribute('value')).toEqual('Unmapped: Deployed');
    // });

    
    it('Select default', function() {
        element(by.id('stat')).click();
        element(by.xpath('//*[@id="stat"]/option[1]')).click();
        //logout
        element(by.id('navbarDropdown')).click();
        element(by.id('logout')).click();
    });
    
});