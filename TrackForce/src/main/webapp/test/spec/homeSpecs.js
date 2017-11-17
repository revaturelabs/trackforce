/*
* @author Benjamin Kidd
Run the Home Page Spec Test File by:
1 - Uncomment the name of the protractor test file in the conf.js file 
2 - Open cmd line in src/main/webapp/test/spec folder
3 - Type protractor conf.js into cmd line

Batch Protractor Test runs the following:
1 - tests that the home page loads correctly
  


*/
describe('Home Page Specs', function() {
  it('should add a todo', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/');
    
    var popButton = element(By.xpath('/html/body/div/div/div[1]/div[4]/div/button[1]'));
    expect(popButton.getText()).toContain('Populate Database');
  });
});