import { browser, by, element } from 'protractor';
import { ElementFinder } from 'protractor/built/element';

export class CreateUserPage{

    private username            : ElementFinder;
    private password            : ElementFinder;
    private passwordConfirm     : ElementFinder;
    private adminRadio          : ElementFinder;
    private associateRadio      : ElementFinder;
    private directorRadio       : ElementFinder;
    private vpRadio             : ElementFinder;

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
         this.directorRadio = this.getDirectorRadio();
         this.vpRadio = this.getVPRadio();
    }

    /**
     * Returns the Create User page
     */
    navigateTo(){
        return browser.get('/createUser');
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
        return element(by.css('[value="admin"]'));
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
        return element(by.css('[value="assoc"]'));
    }

    /**
     * Clicks the associate radio button element in the DOM
     */
    clickAssociateRadio(){
        this.associateRadio.click();
    }

    /**
     * Returns the director radio button element in the DOM
     */
    private getDirectorRadio(){
        return element(by.css('[value="direct"]'));
    }

    /**
     * Clicks the director radio button elemeing in the DOM
     */
    clickDirectorRadio(){
        this.directorRadio.click();
    }
    
    /**
     * Returns the vp radio button element in the DOM
     */
    private getVPRadio(){
        return element(by.css('[value="vp"]'));
    }

    /**
     * Clicks the vp radio button elemeing in the DOM
     */
    clickVPRadio(){
        this.vpRadio.click();
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
}