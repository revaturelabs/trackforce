describe('Testing associate list by curriculum', function () {
    it('Should select Java option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[2]')).click();
        
    });

    it('Should select .Net option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[3]')).click();
    
    });

    it('Should select SEED option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[4]')).click();
        
    });

    it('Should select Salesfoce option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[5]')).click();
       
    });

    it('Should select JTA option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[6]')).click();
    
    });

    it('Should select None option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[7]')).click();
        
    });

    it('Should select DynamicCRM option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[8]')).click(); 
       
    });

    it('Should select PEGA option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[9]')).click();
      
    });

    it('Should select default option', function() {
        wd.findElement(by.xpath('//*[@id="curriculum"]/option[1]')).click();
        
    });

});