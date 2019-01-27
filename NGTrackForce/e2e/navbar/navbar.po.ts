import { protractor, browser, by, element } from 'protractor';

export class Navbar {

    logIn(username, password){
      element(by.name('username')).sendKeys(username);
      element(by.name('password')).sendKeys(password);
      element(by.buttonText('SIGN IN')).click();
    }

    goToAdminHome(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Home'))), 5000);
      element(by.linkText('Home')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(1)')).click();

    }
    goToTrainerHome(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.id('home'))), 5000);
      element(by.id('home')).click();
    }

    goToClientList(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Client List'))), 5000);
      element(by.linkText('Client List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(2)')).click();
    }

    goToBatchList(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Batch List'))), 5000);
      element(by.linkText('Batch List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(3)')).click();
    }

    goToAssociateList(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Associate List'))), 5000);
      element(by.linkText('Associate List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(4)')).click();
    }

    //html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button
    goToCreateUser(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Create User'))), 5000);
      element(by.linkText('Create User')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(6)')).click();
    }

    goToPredictions(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Prediction List'))), 5000);
      element(by.linkText('Prediction List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(5)')).click();
    }

    getCurrentURL(){
        return browser.getCurrentUrl();
    }

    goToAssociateHome(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Home'))), 5000);
      element(by.linkText('Home')).click();
    }
    goToMyInterview(){
      let ec = protractor.ExpectedConditions;
      browser.wait(ec.visibilityOf(element(by.linkText('Interviews'))), 5000);
      element(by.id('interviews')).click();
    }
}
