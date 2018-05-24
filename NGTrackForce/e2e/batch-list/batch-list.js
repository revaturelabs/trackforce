

describe("Batch List Tab Protractor Test (Open site from cmd): "
		+ "Go to site, login, click batch list tab", function() {
	it("Should go to login page", function() {
		wd = browser.driver;
		browser.waitForAngularEnabled(false);
		wd.get('http://localhost:4200/login');
		expect(
				wd.findElement(by.name('username')).getAttribute(
						"value")).toEqual("");
	});
});

describe("Batch List Tab Protractor Test (Login)", function() {
	it("Should login",
			function() {

				browser.manage().timeouts().implicitlyWait(50000);
				browser.ignoreSynchronization = true;
				wd.findElement(by.id('username')).sendKeys('TestAdmin');
				wd.findElement(by.id('password')).sendKeys('TestAdmin');
				wd.findElement(by.xpath("//button[@type='submit']")).click();
		
		console.log(browser.driver.getTitle());
		
		expect(browser.driver.getTitle()).toEqual('TrackForce');
			});

});

describe("Batch List Tab Protractor Test (Click Batch List Tab)", function() {
	it('Should be able to switch to Batch List Tab', function() {
		wd.sleep(3000);
		element(by.xpath('/html/body/app/app-root/div/app-navbar/nav/div/ul[1]/li[4]/a')).click();
		wd.sleep(3000);
		// If tab is clicked, find something to match
		
	});
});