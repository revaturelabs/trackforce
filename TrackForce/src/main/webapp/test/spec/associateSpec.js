/*
 * @author Benjamin Kidd
 * 
Run the Associates Spec Test File by:
1 - Uncomment the name of the protractor test file in the conf.js file 
2 - Open cmd line in src/main/webapp/test/spec folder
3 - Type protractor conf.js into cmd line

Associates Protractor Test runs the following:
1 - test functionality of search box
2 - tests functionality of all associate mapping types
  

*/

describe('Client Page Specs', function() {
  it('client page tests', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/associateListing');
    browser.ignoreSynchronization = true;
   
    //test headerTitle
    var headerTitle = element(By.xpath('/html/body/div/div/div/div[1]/div/div[1]/label'));
    expect(headerTitle.getText()).toContain('Search By Text');
    
    //test associate search box
    var searchBox = element(By.xpath('//*[@id="name"]'));

    //Enter Benjamin into search box and check header title
    searchBox.clear();
    searchBox.sendKeys('Benjamin');
    //browser.sleep('2000');
    var ben = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[2]'));
    expect(browser.isElementPresent(ben)).toBe(true);

    //Test Select Unmapped-Training  
    searchBox.clear();
    searchBox.sendKeys('Benjamin');
    var dropdownUnmappedTraining = element(By.xpath('//*[@id="mStatus"]/option[7]'));
    dropdownUnmappedTraining.click();
    var lastNameUnmapped = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameUnmapped.getText()).toContain('Kidd');   
   
    
    //Test Select Mapped-Training
    searchBox.clear();
    searchBox.sendKeys('Benjamin');
    var dropdownMappedTraining = element(By.xpath('//*[@id="mStatus"]/option[1]'));
    dropdownMappedTraining.click();
    var lastNameMapped = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMapped.getText()).toContain('Rogers'); 
    
    
    //Test Select Mapped-Deployed
    searchBox.clear();
    searchBox.sendKeys('Benjamin');
    var dropdownMappedDeployed = element(By.xpath('//*[@id="mStatus"]/option[5]'));
    dropdownMappedDeployed.click();
    var lastNameMappedDeployed = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[3]'));
    expect(lastNameMappedDeployed.getText()).toContain('Wingo');
  
    
    //Test Select Mapped-Reserved
    searchBox.clear();
    searchBox.sendKeys('Michael');
    var dropdownMappedReserved = element(By.xpath('//*[@id="mStatus"]/option[2]'));
    dropdownMappedReserved.click();
    var lastNameMappedReserved = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedReserved.getText()).toContain('Garza');

    
    //Test Select Mapped-Selected   -- COMMENTED OUT - NO NAMES TO TEST
    //var dropdownMappedSelected = element(By.xpath('//*[@id="mStatus"]/option[3]'));
    //dropdownMappedSelected.click();
    //var lastNameMappedSelected = element(By.xpath(''));
    //expect(lastNameMappedSelected.getText()).toContain('');
	
    
    //Test Select Mapped-Confirmed 
    searchBox.clear();
    searchBox.sendKeys('Patrick');
    var dropdownMappedConfirmed = element(By.xpath('//*[@id="mStatus"]/option[4]'));
    dropdownMappedConfirmed.click();
    var lastNameMappedConfirmed = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedConfirmed.getText()).toContain('Muldoon');
    
    
    //Test Select UnMapped-Open 
    searchBox.clear();
    searchBox.sendKeys('Albert');
    var dropdownMappedConfirmed2 = element(By.xpath('//*[@id="mStatus"]/option[7]'));
    dropdownMappedConfirmed2.click();
    var lastNameMappedConfirmed2 = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedConfirmed2.getText()).toContain('Vasilyev');
    
    
    //Test Select UnMapped-Selected 
    searchBox.clear();
    searchBox.sendKeys('Patrick');
    var dropdownMappedConfirmed3 = element(By.xpath('//*[@id="mStatus"]/option[8]'));
    dropdownMappedConfirmed3.click();
    var lastNameMappedConfirmed3 = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedConfirmed3.getText()).toContain('Kennedy');
    
    
    //Test Select UnMapped-Confirmed
    searchBox.clear();
    searchBox.sendKeys('John');
    var dropdownMappedConfirmed4 = element(By.xpath('//*[@id="mStatus"]/option[9]'));
    dropdownMappedConfirmed4.click();
    var lastNameMappedConfirmed4 = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedConfirmed4.getText()).toContain('Villamar');
    
    
    //Test Select UnMapped-Deployed
    searchBox.clear();
    searchBox.sendKeys('Daniel');
    var dropdownMappedConfirmed5 = element(By.xpath('//*[@id="mStatus"]/option[10]'));
    dropdownMappedConfirmed5.click();
    var lastNameMappedConfirmed5 = element(By.xpath('/html/body/div/div/div/div[2]/table/tbody/tr/td[3]'));
    expect(lastNameMappedConfirmed5.getText()).toContain('Zorrilla');
   
    
  });
});