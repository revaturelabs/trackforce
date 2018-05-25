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
        wd.findElement(by.id('username')).sendKeys('TestAdmin');
	    wd.findElement(by.id('password')).sendKeys('TestAdmin');
        wd.findElement(by.xpath("//button[@type='submit']")).click();
        expect(wd.getTitle()).toEqual('TrackForce');
    })
});

describe('Go to associate list tab', function(){
    console.log(wd.getTitle());
    
    it('Should be able to go to associate list tab.', function() {
        browser.manage().timeouts().implicitlyWait(5000);
        element(by.xpath('/html/body/app/app-root/div/app-navbar/nav/div/ul[1]/li[5]/a')).click();
        wd.sleep(3000);
    });

    it('We should be on the associate tab', function () {
        expect(wd.findElement(by.xpath('/html/body/app/app-associate-list/div/h3')).getText()).toEqual('Associates');
    });

});






