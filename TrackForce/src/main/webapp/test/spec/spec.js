// spec.js
describe('Protractor Index Page Test', function() {
  
	var hTitle = element.all(by.xpath('/html/body/div/div/div/h2'));
	var hTitleBatch = element.all(by.xpath('/html/body/div/div/section/div/div[2]/div[1]/h3'));
	var hTitleClient = element.all(by.xpath('/html/body/div/div[1]/div[1]/div[1]/div/h3'));
	

	it('Test Home Page Load', function() {
	browser.get('http://localhost:8080/TrackForce/html/index.html#!/');
    expect(hTitle.getText()).toContain('This is the home page'); 
	});

	it('Test Batch Page Load', function() {
	browser.get('http://localhost:8080/TrackForce/html/index.html#!/batchListing');
    expect(hTitleBatch.getText()).toContain('All Batches'); 
	});

	it('Test Client Page Load', function() {
	browser.get('http://localhost:8080/TrackForce/html/index.html#!/clientDetails');
    expect(hTitleClient.getText()).toContain('Clients'); 
	});

	
});