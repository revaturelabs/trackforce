describe('Lets do some stuff in the associates list tab...', function () {
    it('Test out search by name of associate', function() {
        associate = "Steven";
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(associate);
    });
    
    it('Check return results of entered values, with name of associate', function() {
        expect(wd.findElement(by.xpath('//*[@id="info"]/table/tbody/tr[1]/td[3]')).getText()).toEqual(associate);
    });

    it('Clear the search input box', function() {
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(protractor.Key.chord(protractor.Key.CONTROL, "a"));
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(protractor.Key.BACK_SPACE);
    });

    it('Test out search by last name',function() {
        associateLast = "Cox";
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(associateLast);
    });

    it('Check results of search by associates last name', function(){
        expect(wd.findElement(by.xpath('//*[@id="info"]/table/tbody/tr[1]/td[4]')).getText()).toEqual(associateLast);
    });

    it('Clear out search input once more', function(){
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(protractor.Key.chord(protractor.Key.CONTROL, "a"));
        wd.findElement(by.xpath('//*[@id="name"]')).sendKeys(protractor.Key.BACK_SPACE);
    });

});
