import { browser, by, element } from 'protractor';
import { ElementFinder } from 'protractor/built/element';

export class CreateUserPo {

    private username            : ElementFinder;
    private password            : ElementFinder;
    private passwordConfirm     : ElementFinder;
    private adminRadio          : ElementFinder;
    private managerRadio        : ElementFinder;
    private trainerRadio        : ElementFinder;
    private associateRadio      : ElementFinder;
    private deliveryRadio       : ElementFinder;

    /**
     * Stores each element on the Create User page:
     * Username - input field
     * Password - input field
     * PasswordConfirm - input field
     * AdminRadio - radio button
     * AssociateRadio - radio button
     * DirectorRadio - radio button
     * VPRadio - radio button
     */
    constructor() {
         this.username = this.getUsernameInput();
         this.password = this.getPasswordInput();
         this.passwordConfirm = this.getPasswordConfirmInput();
         this.adminRadio = this.getAdminRadio();
         this.associateRadio = this.getAssociateRadio();
         this.managerRadio = this.getManagerRadio();
         this.trainerRadio = this.getTrainerRadio();
         this.deliveryRadio = this.getDeliveryRadio();
    }

    /**
     * Returns the Create User page
     */
    navigateTo(){
        return browser.get('');
    }

    /**
     * Returns the current URL in the browser
     */
    getCurrentURL(){
        return browser.getCurrentUrl();
    }

    /**
     * Returns the username element in the DOM
     */
    private getUsernameInput(){
        return element(by.css('[name="username"]'));
    }

    /**
     * Sends the given input string to the username element in the DOM
     * @param input
     */
    inputUsername(input: string){
        this.username.sendKeys(input);
    }

    /**
     * Returns the current value of the username element in the DOM
     */
    getUsernameValue(){
        return this.username.getAttribute('value');
    }

    /**
     * Returns the password element in the DOM
     */
    private getPasswordInput(){
        return element(by.css('[name="password"]'));
    }

    /**
     * Sends the given input string to the password element in the DOM
     * @param input
     */
    inputPassword(input: string){
        this.password.sendKeys(input);
    }

    /**
     * Returns the current value of the password element in the DOM
     */
    getPasswordValue(){
        return this.getPasswordInput().getAttribute('value');
    }

    /**
     * Returns the password confirmation element in the DOM
     */
    private getPasswordConfirmInput(){
        return element(by.css('[name="password2"]'));
    }

    /**
     * Sends the given input string to the password confirmation element in the DOM
     * @param input
     */
    inputPasswordConfirm(input: string){
        this.passwordConfirm.sendKeys(input);
    }

    /**
     * Returns the current value of the password confirmation element in the DOM
     */
    getPasswordConfirmValue(){
        return this.passwordConfirm.getAttribute('value');
    }

    /**
     * Returns the admin radio button element in the DOM
     */
    private getAdminRadio(){
      //  return element(by.cssContainingText('optradio','admin'));
        return element(by.css('[value="1"]'));
    }

    /**
     * Clicks the admin radio button element in the DOM
     */
    clickAdminRadio(){
        this.adminRadio.click();
    }

    /**
     * Returns the associate radio button element in the DOM
     */
    private getAssociateRadio(){
        return element(by.css('[value="5"]'));
    }

    /**
     * Clicks the associate radio button element in the DOM
     */
    clickAssociateRadio(){
        this.associateRadio.click();
    }

    /**
     * Returns the manager radio button element in the DOM
     */
    private getManagerRadio(){
        return element(by.css('[value="4"]'));
    }

    /**
     * Clicks the manager radio button elemeing in the DOM
     */
    clickManagerRadio(){
        this.managerRadio.click();
    }

     /**
     * Returns the delivery radio button element in the DOM
     */
    private getDeliveryRadio(){
        return element(by.css('[value="3"]'));
    }

    /**
     * Clicks the delivery radio button elemeing in the DOM
     */
    clickDeliveryRadio(){
        this.deliveryRadio.click();
    }
    /**
     * Returns the trainer radio button element in the DOM
     */
    private getTrainerRadio(){
        return element(by.css('[value="2"]'));
    }

    /**
     * Clicks the trainer radio button elemeing in the DOM
     */
    clickTrainerRadio(){
        this.trainerRadio.click();
    }

    /**
     * Returns the checked radio element if one a radio element is checked
     */
    getCheckedRadioValue(){
        if(element(by.css('[type="checked"]'))){
            return element(by.css(':checked')).getAttribute('value');
        }
        else {
            return '';
        }
    }
    
    //Changed from xpath to css Jamir & Brandon 1901SDET --success
    private getSubmitButton(){
      return element(by.css('html > body > app-component > div > app-create-user > form > button'));
    }

    clickSubmitButton(){
      this.getSubmitButton().click();
    }

    getAlert(){
      return element(by.css('body > app-component > div > app-create-user > form > fieldset:nth-child(2) > div'));
    }
    getUserNameAlert(){
      return element(by.xpath('//*[@id="error-username"]'));
    }
    refresh(){
      return browser.refresh();
    }
}
