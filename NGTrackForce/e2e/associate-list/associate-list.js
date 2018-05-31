
describe('Open the browser and get to the website', function() {
    // var PropertiesReader = require('properties-reader');
    // var properties = PropertiesReader('../testing.properties');
    wd = browser.driver;
});

describe('Log into the website for associate list', function() {
    browser.manage().timeouts().implicitlyWait(50000);
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
        element(by.css('[routerlink="/associate-listing"]')).click();
    });

    it('We should be on the associate tab', function () {
        expect(wd.findElement(by.xpath('/html/body/app/app-associate-list/div/h3')).getText()).toEqual('Associates');
    });

});






