describe('Testing associate list page further by changing search status.', function() {
    it('Select mapped: training', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[2]')).click();
        wd.sleep(1000);
    });

    it('Verify mapped training selection', function () {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('MAPPED: TRAINING');
    });

    it('Select mapped: reserved', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[3]')).click();
        wd.sleep(500);
    });

    it('Verify mapped reserved selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('MAPPED: RESERVED');
    });

    it('Select MAPPED: SELECTED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[4]')).click();
        wd.sleep(500);
    });

    it('Verify MAPPED: SELECTED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('MAPPED: SELECTED');
    });

    it('Select MAPPED: CONFIRMED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[5]')).click();
        wd.sleep(500);
    });

    it('Verify MAPPED: CONFIRMED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('MAPPED: CONFIRMED');
    });

    it('Select MAPPED: DEPLOYED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[6]')).click();
        wd.sleep(500);
    });

    it('Verify MAPPED: DEPLOYED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('MAPPED: DEPLOYED');
    });

    it('Select UNMAPPED: TRAINING', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[7]')).click();
        wd.sleep(500);
    });

    it('Verify UNMAPPED: TRAINING selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('UNMAPPED: TRAINING');
    });

    it('Select UNMAPPED: OPEN', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[8]')).click();
        wd.sleep(500);
    });

    it('Verify UNMAPPED: OPEN selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('UNMAPPED: OPEN');
    });

    it('Select UNMAPPED: SELECTED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[9]')).click();
        wd.sleep(500);
    });

    it('Verify UNMAPPED: SELECTED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('UNMAPPED: SELECTED');
    });

    it('Select UNMAPPED: CONFIRMED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[10]')).click();
        wd.sleep(500);
    });

    it('Verify UNMAPPED: CONFIRMED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('UNMAPPED: CONFIRMED');
    });

    it('Select UNMAPPED: DEPLOYED', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[11]')).click();
        wd.sleep(500);
    });

    it('Verify UNMAPPED: DEPLOYED selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('UNMAPPED: DEPLOYED');
    });
    
    it('Select default', function() {
        wd.findElement(by.xpath('//*[@id="mStatus"]/option[1]')).click();
        wd.sleep(500);
    });

    it('Verify default selection', function() {
        expect(wd.findElement(by.id('mStatus')).getAttribute('ng-reflect-model')).toEqual('');
    });
    
});