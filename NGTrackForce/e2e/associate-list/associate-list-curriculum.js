describe('Testing associate list by curriculum', function () {
    it('Should select Java option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[2]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('Java');
    });

    it('Should select .Net option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[3]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('.Net');
    });

    it('Should select SEED option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[4]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('SEED');
    });

    it('Should select Salesfoce option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[5]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('Salesforce');
    });

    it('Should select JTA option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[6]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('JTA');
    });

    it('Should select None option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[7]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('None');
    });

    it('Should select DynamicCRM option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[8]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('DynamicCRM');
    });

    it('Should select PEGA option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[9]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('PEGA');
    });

    it('Should select default option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[1]')).click();
        
        expect(wd.findElement(by.xpath('//*[@id="curriculum"]')).getAttribute('ng-reflect-model')).toEqual('');
    });

});