describe('Home Page Specs', function() {
  it('should add a todo', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/');
    
    var popButton = element(By.xpath('/html/body/div/div/div[1]/div[4]/div/button[1]'));
    expect(popButton.getText()).toContain('Populate Database');
  });
});