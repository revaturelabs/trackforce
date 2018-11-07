describe('test assocation pagenation functionality', function() {
    
    browser.get('http://localhost:4200/login');
    browser.manage().timeouts().implicitlyWait(5000);
    it('should log in', function() {
  
      element(by.id('username')).sendKeys('TestAdmin');
      element(by.id('password')).sendKeys('TestAdmin');
      element(by.xpath("//button[@type='submit']")).click();
      expect(element(by.id('home')).getText()).toEqual('Home');
      
    });

    it('Go to associate list tab', function() {
    
        element(by.id('associates')).click();
        expect(element(by.id('tableScroller')).isPresent()).toBe(true);

    });

    it('Should load 60 or less associates.', function() {
    expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(60);

    });

    it('Should load more associate if page scrolls down and theres more than 60.', function() {

        //When Scolling to the bottom of the page, and there are 60 or more associates
        //More associates should load in
        if(element(by.css("table tbody tr:nth-child(60)")).isPresent){
            browser.actions().mouseMove(element(by.css("table tbody tr:nth-child(60)"))).perform();
            expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(120);
        //In case there are less than 60 associates
        }else{
            expect(element.all(by.css("table tbody tr")).count()).toBeLessThanOrEqual(60);
        }
        
    });

  });