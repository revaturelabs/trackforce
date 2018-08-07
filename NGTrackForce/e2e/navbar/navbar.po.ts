import { browser, by, element } from 'protractor';

export class Navbar {

    navigateTo(){
        return browser.get('');
    }

    goToHome(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(1)')).click();
      
    }

    goToClientList(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(2)')).click();
    }

    goToBatchList(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(3)')).click();
    }

    goToAssociateList(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(4)')).click();
    }

    logout(){
        element(by.css('body > app-component > app-navbar > nav > div > ul.nav.navbar-nav.navbar-right.visible-lg > li:nth-child(1)')).click();
    }

    goToCreateUser(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(6)')).click();
    }

    goToPredictions(){
        element(by.css('body > app-component > app-navbar > nav > div > ul:nth-child(2) > li:nth-child(5)')).click();
    }
    getCurrentURL(){
        return browser.getCurrentUrl();
    }
}