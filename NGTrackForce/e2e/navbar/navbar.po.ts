import { browser, by, element } from 'protractor';

export class Navbar {

    navigateTo(){
        return browser.get('');
    }

    goToHome(){
        element(by.css('[routerlink="/root"]')).click();
    }

    goToClientList(){
        element(by.css('[routerlink="/client-listing"]')).click();
    }

    goToBatchList(){
        element(by.css('[routerlink="/batch-listing"]')).click();
    }

    goToAssociateList(){
        element(by.css('[routerlink="/associate-listing"]')).click();
    }

    logout(){
        element(by.css('[routerLink="/login"]')).click();
    }

    goToCreateUser(){
        element(by.css('[routerlink="/create-user"]')).click();
    }

    goToPredictions(){
        element(by.css('[routerlink="/predictions"]')).click();
    }
    getCurrentURL(){
        return browser.getCurrentUrl();
    }
}