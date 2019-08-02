
describe('Trainer only sees associates in their batches', () => {
    it ('logs in and goes to associates page', () => { 
        browser.get('http://localhost:4200/#/login');
        browser.driver.sleep(10000);
        element(by.id('username')).sendKeys('Trainer0');
        element(by.id('password')).sendKeys('Trainer');
        browser.driver.sleep(5000);
        element(by.id('associates')).click();
        browser.driver.sleep(10000);
        let associates = JSON.parse(localStorage.getItem('associatePage|/page?startIndex=0&numResults=60'));
        let x = 0;
        let validation = 0;
        for (x = 0; x < associates.length; x++){
            if (associate[x].firstName === 'Samuel'){
                validation = 1;
                break;
            }
        }
        expect(validation).toBe(1);
    });
});