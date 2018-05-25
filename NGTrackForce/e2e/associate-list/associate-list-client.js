describe('Testing associate list via Client Search', function() {
    it('Should select None',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[2]')).click();
        wd.sleep(1000);
        expect(wd.findElement(by.xpath('//*[@id="client"]')).getAttribute('ng-reflect-model')).toEqual('None');
    });

    it('Should select 22nd Century Technologies',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[3]')).click();
        wd.sleep(1000);
        expect(wd.findElement(by.xpath('//*[@id="client"]')).getAttribute('ng-reflect-model')).toEqual('22nd Century Technologies');
    });

    it('Should select 3 Edge USA Group, LLC',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[4]')).click();
        wd.sleep(1000);
        expect(wd.findElement(by.xpath('//*[@id="client"]')).getAttribute('ng-reflect-model')).toEqual('3 Edge USA Group, LLC');
    });

    it('Should select 3 S Business Corporation Inc (, LLC',function() {
        wd.findElement(by.xpath('//*[@id="client"]/option[5]')).click();
        wd.sleep(1000);
        expect(wd.findElement(by.xpath('//*[@id="client"]')).getAttribute('ng-reflect-model')).toEqual('3 S Business Corporation Inc (');
        wd.quit();
    });

    it('Should select the rest', function() {
        i = 6;
        while (i < 20) {
            wd.findElement(by.xpath('//*[@id="client"]/option['+i+']')).click();
            wd.sleep(100);
            console.log(wd.findElement(by.xpath('//*[@id="client"]/option['+i+']')));
            i++;     
        }
    });

    wd.quit();
});