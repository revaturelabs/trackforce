describe('Client Page Specs', function() {
  it('should add a todo', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/clientDetails');
    browser.ignoreSynchronization = true;
    //test headerTitle
    var headerTitle = element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/h3'));
    expect(headerTitle.getText()).toContain('Clients');
    
    //test client search box
    var searchBox = element(By.xpath('//*[@id="clientSearch"]'));

    
    searchBox.sendKeys('accenture');
    browser.sleep('2000');
    expect(browser.isElementPresent(element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/ul/li[1]/a')))).toBe(true);
    
    //var result = element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/ul/li[1]/a'));
    //expect(result.getText()).toContain('Accenture / Cigna');

    
  });
});