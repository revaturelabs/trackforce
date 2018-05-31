import { browser, by, element } from 'protractor';
import { ElementFinder } from 'protractor/built/element';
import { BasePage, IdentificationType } from '../BasePage';

var header;


export class ClientListPo extends BasePage {

    private searchByClientName       : ElementFinder;
    private viewDataForAllClients    : ElementFinder;

    /**
     * Stores each element on the Client list page:
     * searchByClientName - input field
     * viewDataForAllClients - button
     */
    // constructor() {
    //      this.searchByClientName = this.getSearchByClientName();
    //      this.viewDataForAllClients = this.getAllUsersButton();

    // }
 
    /**
     * Returns the Create List page
     */
    navigateTo(){
        return browser.get('client-listing');
    }

    logout() {
        element(by.css('[routerlink="/login"]')).click();
    }

    /**
     * Returns the current URL in the browser
     */
    getCurrentURL(){
        return browser.getCurrentUrl();
    }

    /**
     * Returns the search by client name element in the DOM
     */
    private getSearchByClientName(){
        return element(by.id('clientSearch'));
    }

    /**
     * Sends the given input string to the search by client name element in the DOM
     * @param input
     */
    inputClientName(input: string){
        this.searchByClientName.sendKeys(input);
    }

    /**
     * Returns the current value of the search by client name element in the DOM
     */
    getClientNameValue(){
       return element(by.id('clientSearch')).getAttribute('ng-reflect-model');
       
    }

    /**
     * Returns the get all users button element in the DOM
     */
    private getAllUsersButton(){
        return element(by.css('body > app > app-client-list > div > div > div.col-md-3 > button'));
    }

    /**
     * Clicks the get all users button element in the DOM
     */
    clickGetAllUsers(){
        this.getAllUsersButton().click();
    }

    /**
     * Returns the search by client name element in the DOM
     */
    public getBarChartHeader(){
        return element(by.xpath('/html/body/app/app-client-list/div/div/div[2]/h1'));
    }

    /**
     * Returns the current value of the search by client name element in the DOM
     */
    getBarChartNameValue(){
        header = this.getBarChartHeader().getAttribute('value');
        return header;
    }

    /**
     * Returns true if the current value of the bar chart header is equal to 'Total Associates', and false if it doesn't
     */
    checkBarChartHeader(){
        var shouldBe = 'Total Associates'
        if(header.equals(shouldBe)){
            return true;
        }
        else {
            return false;
        }
    }
}