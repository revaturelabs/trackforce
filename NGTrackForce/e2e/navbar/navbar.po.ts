import { browser, by, element } from 'protractor';

export class Navbar {

    logIn(username, password){
      element(by.name('username')).sendKeys(username);
      element(by.name('password')).sendKeys(password);
      element(by.buttonText('SIGN IN')).click();
    }

    goToAdminHome(){
      element(by.linkText('Home')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(1)')).click();

    }
    goToTrainerHome(){
      element(by.id('home')).click();
    }

    goToClientList(){
      element(by.linkText('Client List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(2)')).click();
    }

    goToBatchList(){
      element(by.linkText('Batch List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(3)')).click();
    }

    goToAssociateList(){
      element(by.linkText('Associate List')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(4)')).click();
    }

    //html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button
    goToCreateUser(){
      element(by.linkText('Create User')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(6)')).click();
    }

    goToPredictions(){
      element(by.id('predictions')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(5)')).click();
    }

    goToSalesForce(){
      element(by.linkText('Salesforce')).click();
    }
    getCurrentURL(){
        return browser.getCurrentUrl();
    }

    goToAssociateHome(){
      element(by.linkText('Home')).click();
    }
    goToMyInterview(){
      element(by.id('interviews')).click();
    }
}
