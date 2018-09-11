describe("Batch List Tab Protractor Test (Open site from cmd): "
		+ "Go to site, login, click batch list tab", function() {
	it("Should go to login page", function() {
		wd = browser.driver;
		expect(wd.findElement(by.name('username')).getAttribute("value")).toEqual("");
	});
});

describe("Batch List Tab Protractor Test (Login)", function() {
	it("Should login", function() {
				browser.manage().timeouts().implicitlyWait(50000);
				wd.findElement(by.id('username')).sendKeys('TestAdmin');
				wd.findElement(by.id('password')).sendKeys('TestAdmin');
				wd.findElement(by.xpath("//button[@type='submit']")).click();


		expect(browser.driver.getTitle()).toEqual('TrackForce');
			});

});

describe("Batch List Tab Protractor Test (Click Batch List Tab)", function() {
	it('Should be able to switch to Batch List Tab', function() {
		browser.driver.sleep(10000);
		element(by.cssSelector("[href='/batch-listing']")).click();
		// If tab is clicked, find something to match

	});

	it('should log out', () => {
	    wd.findElement(by.css('[routerlink="/login"]')).click();
	});
});
