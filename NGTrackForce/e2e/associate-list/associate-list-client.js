describe('Testing associate list via Client Search', function() {

    //NGTrackForce_URL
    browser.get('http://trackforce.revaturelabs.com/NGTrackForce/');
    browser.manage().timeouts().implicitlyWait(5000);

    it('should log in', function() {

        element(by.id('username')).sendKeys('TestAdmin');
        element(by.id('password')).sendKeys('TestAdmin');
        element(by.css('#pwd-container > div > section > form > div > div:nth-child(3) > button:nth-child(1)')).click();
        expect(element(by.id('home')).getText()).toEqual('Home');

    });

    it('Go to associate list tab', function() {

        element(by.id('associates')).click();
        expect(element(by.id('tableScroller')).isPresent()).toBe(true);

    });

    it('Should select None', function() {
        element(by.id('client')).click();
        element(by.xpath('//*[@id="client"]/option[2]')).click();
        expect(element(by.id('client')).getAttribute('value')).toMatch('0');

    });

    it('Should select 22nd Century Technologies', function() {
        element(by.id('client')).click();
        element(by.xpath('//*[@id="client"]/option[3]')).click();
        expect(element(by.id('client')).getAttribute('value')).toMatch('1');

    });

    it('Should select 3 Edge USA Group, LLC', function() {
        element(by.id('client')).click();
        element(by.xpath('//*[@id="client"]/option[4]')).click();
        expect(element(by.id('client')).getAttribute('value')).toMatch('2');

    });

    it('Should select 3 S Business Corporation Inc (, LLC', function() {
        element(by.id('client')).click();
        element(by.xpath('//*[@id="client"]/option[5]')).click();
        expect(element(by.id('client')).getAttribute('value')).toMatch('3');
    });

    it('should log out', () => {
        element(by.id('navbarDropdown')).click();
        element(by.id('logout')).click();
    });
});
