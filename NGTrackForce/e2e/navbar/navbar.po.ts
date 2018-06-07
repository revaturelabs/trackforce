import { browser, by, element } from 'protractor';

export class Navbar {

    navigateTo(){
        return browser.get('/');
    }

    goToHome(){
        element(by.css('[routerlink="/home"]')).click();
    }

    goToClientList(){
        element(by.css('[routerlink="/clientListing"]')).click();
    }

    goToBatchList(){
        element(by.css('[routerlink="/batchListing"]')).click();
    }

    goToAssociateList(){
        element(by.css('[routerlink="/associateListing"]')).click();
    }

    logout(){
        element(by.css('[(click)="logout()"]')).click();
    }

    getCurrentURL(){
        console.log(browser.getCurrentUrl());
        return browser.getCurrentUrl();
    }
}