describe('Testing associate list via Client Search', function() {
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

    it('Should select None',function() {
        element(by.id('exampleFormControlSelect3')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect3"]/option[2]')).click();

    });

    it('Should select 22nd Century Technologies',function() {
        element(by.id('exampleFormControlSelect3')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect3"]/option[3]')).click();
    
    });

    it('Should select 3 Edge USA Group, LLC',function() {
        element(by.id('exampleFormControlSelect3')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect3"]/option[4]')).click();
       
    });

    it('Should select 3 S Business Corporation Inc (, LLC',function() {
        element(by.id('exampleFormControlSelect3')).click();
        element(by.xpath('//*[@id="exampleFormControlSelect3"]/option[5]')).click();
    });

    it('Should select some more', function() {
        i = 6;
        while (i < 20) {
            element(by.id('exampleFormControlSelect3')).click();
            element(by.xpath('//*[@id="exampleFormControlSelect3"]/option['+i+']')).click();
            i++;     
        }
    });

    it('should log out', () => {
        //logout
        element(by.id('navbarDropdown')).click();
        element(by.id('logout')).click();
    });
});