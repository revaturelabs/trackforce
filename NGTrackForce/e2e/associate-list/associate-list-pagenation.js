//This test checks the functionality of the association
//pagenation feature which currently loads 60 associates
//on the associate page.  Then when scrolled to the bottom
//Loads 60 more
describe('test assocation pagenation functionality', function () {

  //NGTrackForce_URL
  //browser.get('http://trackforce.revaturelabs.com/NGTrackForce/');
  browser.get('http://localhost:4200/');

  beforeEach(() => {
    browser.manage().timeouts().implicitlyWait(5000);
  });

  it('should log in', function () {

    element(by.id('username')).sendKeys('TestAdmin');
    element(by.id('password')).sendKeys('TestAdmin');
    element(by.css('#pwd-container > div > section > form > div > div:nth-child(3) > button:nth-child(1)')).click();
    expect(element(by.id('home')).getText()).toEqual('Home');
  });

  it('Go to associate list tab', function () {

    element(by.id('associates')).click();
    browser.wait(element(by.id('tableScroller')), 2000, 'Table not found in 2 seconds');
    expect(element(by.id('tableScroller')).isPresent()).toBe(true);

  });

  it('Should load 60 or less associates.', function () {
    expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(60);
  });

  it('Should load more associate if page scrolls down and theres more than 60.', function () {

    //When Scolling to the bottom of the page, and there are 60 or more associates
    //More associates should load in
    if (element(by.css("table tbody tr:nth-child(60)")).isPresent) {
      browser.actions().mouseMove(element(by.css("table tbody tr:nth-child(60)"))).perform();
      expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(121);
      //In case there are less than 60 associates
    } else {
      expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(60);
    }
    //logout
    element(by.id('navbarDropdown')).click();
    element(by.id('logout')).click();
  });

});
