describe('Testing associate list via Client Search', function() {
    it('Should select None',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[2]')).click();

    });

    it('Should select 22nd Century Technologies',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[3]')).click();
    
    });

    it('Should select 3 Edge USA Group, LLC',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[4]')).click();
       
    });

    it('Should select 3 S Business Corporation Inc (, LLC',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[5]')).click();
    });

    it('Should select some more', function() {
        i = 6;
        while (i < 20) {
            wd.findElement(by.xpath('//*[@id="client"]/option['+i+']')).click();
            i++;     
        }
    });

    it('should log out', () => {
        wd.findElement(by.css('[routerlink="/login"]')).click();
    });
});