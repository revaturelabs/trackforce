import { browser, by, element } from 'protractor';

export class Navbar {

    navigateTo(){
        return browser.get('/login');
    }

    logIn(username, password){
      element(by.name('username')).sendKeys(username);
      element(by.name('password')).sendKeys(password);
      element(by.buttonText('Sign in')).click();
    }

    goToAdminHome(){
      element(by.linkText('Home')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(1)')).click();

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

    logout(){
      element(by.buttonText('Logout')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul.nav.navbar-nav.navbar-right.visible-lg > li:nth-child(1)')).click();
    }
    //html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button
    goToCreateUser(){
      element(by.linkText('Create User')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(6)')).click();
    }

    goToPredictions(){
      element(by.linkText('Predictions')).click();
        //element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(5)')).click();
    }
    getCurrentURL(){
        return browser.getCurrentUrl();
    }

    goToAssociateHome(){
      element(by.linkText('Home')).click();
    }
    goToMyInterview(){
      element(by.xpath('/html/body/app-component/app-navbar/nav/div/ul[2]/li[2]/a')).click();
    }
}
