describe("Batch List Tab Protractor Test (Open site from cmd): "
		+ "Go to site, login, click batch list tab", function() {
	it("Should go to login page", function() {
		browser.waitForAngularEnabled(false);
		browser.driver.get('https://dev.assignforce.revaturelabs.com');
		expect(
				browser.driver.findElement(by.name('username')).getAttribute(
						"value")).toEqual("Username");
	});
});

describe("Batch List Tab Protractor Test (Login)", function() {
	it("Should login",
			function() {

				browser.manage().timeouts().implicitlyWait(10000);
				browser.ignoreSynchronization = true;
				browser.driver.findElement(by.id('username')).sendKeys('TestAdmin');
				driver.findElement(by.id('password')).sendKeys('TestAdmin');
				browser.driver.findElement(by.xpath("//button[@type='submit']")).click();
		
		console.log(browser.driver.getTitle());
		
		expect(browser.driver.getTitle()).toEqual('AssignForce');
			});

});

describe("Batch List Tab Protractor Test (Click Batch List Tab)", function() {
	it('Should be able to switch to Batch List Tab', function() {
		browser.driver.sleep(10000);
		element(by.cssSelector("[href='/batch-listing']").click());
		// If tab is clicked, find something to match
		
	});
});