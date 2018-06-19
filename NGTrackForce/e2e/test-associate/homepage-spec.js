describe('Open the browser and get to the website', function() {
    wd = browser.driver;
    wd.get('http://34.227.178.103:8090/NGTrackForce');
    // wd.get('http://localhost:4200/login');

});

describe('Log into the website for associate list', function() {
    browser.manage().timeouts().implicitlyWait(50000);
    it('should be able to log in', function () {
        wd.findElement(By.id('username')).sendKeys('TestAssociate');
	    wd.findElement(By.id('password')).sendKeys('TestAssociate');
        wd.findElement(By.xpath("//button[@type='submit']")).click();
        expect(wd.getTitle()).toEqual('TrackForce');
    });

});

describe('Should test out the navbar', function() {
    it('Should go to client tab', function() {
        wd.findElement(by.css('[routerlink="/client-listing"]')).click();
    });

    it('Should go to batch tab', function() {
        wd.findElement(by.css('[routerlink="/batch-listing"]')).click();
    });

    it('Should go to associates tab', function() {
        wd.findElement(by.css('[routerlink="/associate-listing"]')).click();
    });

    it('Should go to predictions tab', function() {
        wd.findElement(by.css('[routerlink="/predictions"]')).click();
    });
    
    it('should go to interviews tab', function(){
        wd.findElement(by.css('[routerlink="/interviews"]')).click();
    });
});

it('should log out' ,function() {
    wd.findElement(by.css('[routerlink="/login"]')).click();
});

