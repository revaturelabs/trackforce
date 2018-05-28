describe('Open the browser and get to the website', function() {
    browser.ignoreSynchronization = true;
    wd = browser.driver;
    browser.waitForAngularEnabled(false);
    wd.get('http://localhost:4200/login');

});

describe('Log into the website for associate list', function() {
    browser.manage().timeouts().implicitlyWait(50000);
    browser.ignoreSynchronization = true;
    it('should be able to log in', function () {
        wd.findElement(by.id('username')).sendKeys('TestAssociate');
	    wd.findElement(by.id('password')).sendKeys('TestAssociate');
        wd.findElement(by.xpath("//button[@type='submit']")).click();
        expect(wd.getTitle()).toEqual('TrackForce');
        wd.sleep(3000);
    });

});

describe('Head over to the My interviews page and do something', function() {
    it('should click the Myvinterviews button', function() {
        wd.findElement(by.xpath('/html/body/app/app-associate-view/app-navbar/nav/div/ul[1]/li[2]/a')).click();
        expect(wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/h3')).getText()).toEqual('Interviews');
    });

    it('should toggle the create new form button', function() {
        wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/button')).click();
        wd.sleep(2000);
    });

    it('should select first client in list and verify', function() {
        client = wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[1]/div/select/option')).getText();
        wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[1]/div/select/option')).click();
        expect(wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[1]/div/select')).getAttribute('ng-reflect-model')).toEqual(client);
    });

    it('should select interview type, phone,  and verify', function() {
        wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[3]/div/select/option[1]')).click();
        expect(wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[3]/div/select')).getAttribute('ng-reflect-model')).toEqual("1");
    });

    it('should enter something in feedback', function() {
        testText = "Something written by Test";
        wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[4]/div/textarea')).sendKeys(testText);
        expect(wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/div/div/form/div[4]/div/textarea')).getAttribute('ng-reflect-model')).toEqual(testText);
    });
    
    it('should toggle the form button again to close it', function() {
        wd.findElement(by.xpath('/html/body/app/app-myinterview-view/div/div/div/button')).click();
        wd.sleep(2000);
    });

    it('should log out', function() {
        wd.findElement(by.css('a[href*="/login"]')).click();  // xpath does not work on this logout button
    });

});

