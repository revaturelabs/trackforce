describe('Testing associate list page further by changing search status.', function() {
    it('Select mapped: training', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[2]')).click();
    });

    it('Select mapped: reserved', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[3]')).click();
    });

    it('Select MAPPED: SELECTED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[4]')).click();
    });

    it('Select MAPPED: CONFIRMED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[5]')).click();
    });

    it('Select MAPPED: DEPLOYED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[6]')).click();
    });

    it('Select UNMAPPED: TRAINING', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[7]')).click();
    });

    it('Select UNMAPPED: OPEN', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[8]')).click();
    });

    it('Select UNMAPPED: SELECTED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[9]')).click();
    });


    it('Select UNMAPPED: CONFIRMED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[10]')).click();
    });


    it('Select UNMAPPED: DEPLOYED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[11]')).click();
    });

    
    it('Select default', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[1]')).click();
    });
    
});