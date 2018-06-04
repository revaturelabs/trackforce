describe("Now ordering by ...", function() {
    it('should order by ID', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[2]')).click();
    });

    it('should order by first name', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[3]')).click();
    });
    it('should order by last name', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[4]')).click();
    });

    it('should order by Marketing status', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[5]')).click();
    });

    it('should order by Client name', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[6]')).click();
    });

    it('should order by Batch name', function() {
        wd.findElement(by.xpath('//*[@id="info"]/table/thead/tr/th[7]')).click();
    });
});