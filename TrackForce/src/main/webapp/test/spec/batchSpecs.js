describe('Home Page Specs', function() {
  it('home page test', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/batchListing');
    browser.ignoreSynchronization = true;
    
    
    //Check Batch Page Header Title
    var headerTitle = element(By.xpath('/html/body/div/div/div/div[2]/div[1]/h3'));
    expect(headerTitle.getText()).toContain('All Batches');
    
    //Check That Date Range Jan 1, 2017 through December 31, 2017 Displays Expected Batches
    var startDate = element(By.xpath('/html/body/div/div/div/div[2]/div[2]/form/input[1]'));
    var endDate = element(By.xpath('/html/body/div/div/div/div[2]/div[2]/form/input[2]'));
    var submitButton = element(By.xpath('/html/body/div/div/div/div[2]/div[2]/form/input[3]'));
    
    startDate.sendKeys('01/01/2017');
    endDate.sendKeys('12/12/2017');    
    submitButton.click();
    browser.sleep(3000);
    var batch1701 = element(By.xpath('/html/body/div/div/div/div[2]/div[1]/div/div/table/tbody/tr[1]/td[1]/a'));
    expect(batch1701.getText()).toContain('1701 Jan09 Java');
    
    //Check Batch1701 Link when Clicked
    var headerBatch1701 = element(By.xpath('/html/body/div/div/div/div[1]/h3'));
    batch1701.click();
    browser.sleep(3000);
    expect(headerBatch1701.getText()).toContain('Batch Details: 1701 Jan09 Java');
    
    //Check AssociateID Link when Clicked
    var associate1 = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[1]/a'));
    associate1.click();
    browser.sleep(3000);
    var associate1LastName = element(By.xpath('/html/body/div/div/div/div[2]/div/table/thead/tr[3]/td'));
    expect(associate1LastName.getText()).toContain('Simmons');
    
    
    
    });
  
});