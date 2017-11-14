describe('Client Page Specs', function() {
  it('client page tests', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/clientDetails');
    browser.ignoreSynchronization = true;
   
    //test headerTitle
    var headerTitle = element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/h3'));
    expect(headerTitle.getText()).toContain('Clients');
    
    //test client search box
    var searchBox = element(By.xpath('//*[@id="clientSearch"]'));

    //Enter Accenture into search box and check header title
    searchBox.sendKeys('accenture');
    //browser.sleep('2000');
    var accentureFederal = element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/ul/li[1]/a'));
    expect(browser.isElementPresent(accentureFederal)).toBe(true);
    
    //Click Accenture Federal Link and Test Header Title
    accentureFederal.click();
    var headerAccenture = element(By.xpath('/html/body/div/div[1]/div[1]/div[2]/h1'));
    expect(headerAccenture.getText()).toContain('Accenture Federal Services');
    
    //Click "View Data for All Clients" Button and Test Header Title
    var dataButton = element(By.xpath('/html/body/div/div[1]/div[1]/div[1]/div/button'));
    var headerAssociates = element(By.xpath('/html/body/div/div[1]/div[1]/div[2]/h1'));
    dataButton.click();
    expect(headerAssociates.getText()).toContain('All associates');
  });
});