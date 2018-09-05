import { browser, by, element } from 'protractor';
import { ElementFinder } from 'protractor/built/element';
import { BasePage, IdentificationType } from '../BasePage';

let header;

const Locators = {
    clientSearch: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="clientSearch"]'
    },
    clientResultList: {
        type: IdentificationType[IdentificationType.ElementsByXpath],
        value: '//ul[@id="clients-list"]/li'
    },
    allClientsDataBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[contains(text(), "lients")]'
    },
    barChartHeader: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '/html/body/app-component/div/app-client-list/div/div/div[2]/h1'
    },
    clientListSpan: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//ul[@id="clients-list"]/li[1]/span'
    }
}

export class ClientListPo extends BasePage {
    clientSearch = this.ElementLocator(Locators.clientSearch);
    clientResultList = this.ElementLocator(Locators.clientResultList);
    allClientsDataBtn = this.ElementLocator(Locators.allClientsDataBtn);
    barChartHeader = this.ElementLocator(Locators.barChartHeader);
    clientListSpan = this.ElementLocator(Locators.clientListSpan);
    searchByClientName: ElementFinder;
    viewDataForAllClients: ElementFinder;

    startUp(){
      return browser.get('/login')
    }

    logIn(username, password){
      element(by.name('username')).sendKeys(username);
      element(by.name('password')).sendKeys(password);
      element(by.buttonText('Sign in')).click();
    }

    /**
     * Returns the Create List page
     */
    navigateTo(){
        element(by.linkText('Client List')).click();
    }

    logout() {
      element(by.xpath('/html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button')).click();
    }


    /**
     * Returns the current URL in the browser
     */
    getCurrentURL() {
        return browser.getCurrentUrl();
    }

    /**
     * Returns the search by client name element in the DOM
     */
    private getSearchByClientName(){
        return this.clientSearch;
    }

    /**
     * Sends the given input string to the search by client name element in the DOM
     * @param input
     */
    inputClientName(){
        return this.clientSearch;
    }

    getClientResultListCount() {
        return this.clientResultList.count();
    }

    /**
     * Returns the get all users button element in the DOM
     */
    private getAllClientDataBtn(){
        return this.allClientsDataBtn;
    }

    /**
     * Clicks the get all users button element in the DOM
     */
    clickGetAllClientDataBtn()
      {
        this.getAllClientDataBtn().click();
      }

    /**
     * Returns the search by client name element in the DOM
     */
    public getBarChartHeader(){
        return this.barChartHeader;
    }

    /**
     * Returns the current value of the search by client name element in the DOM
     */
    getBarChartNameValue() {
        header = this.getBarChartHeader().getAttribute('value');
        return header;
    }

    /**
     * Returns true if the current value of the bar chart header is equal to 'Total Associates', and false if it doesn't
     */
    checkBarChartHeader(){
        let shouldBe = 'Total Associates'
        if(header.equals(shouldBe)){
            return true;
        }
        else {
            return false;
        }
    }

    getLogoutButton() {
        return element(by.buttonText('Logout'));
    }
}
