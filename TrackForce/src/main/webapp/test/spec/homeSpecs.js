describe('Home Page Specs', function() {
  it('should add a todo', function() {
    browser.get('http://localhost:8080/TrackForce/html/index.html#!/');
    
    var headerTitle = element(By.xpath('/html/body/div/div/div/h2'));
    expect(headerTitle.getText()).toContain('This is the home page');
  });
});