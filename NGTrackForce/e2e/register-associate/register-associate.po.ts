import { BasePage, IdentificationType } from "../BasePage";
import { browser, by, element } from 'protractor';
import { LoginPage } from "../login/login.po";

const Locators = {
    registerBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="pwd-container"]/div/section/form/div/div[2]/button[2]'
    },
    usernameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="username"]'
    },
    passwordInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="password"]'
    },
    cPasswordInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="cpassword"]'
    },
    nextPageBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[contains(text(), "ext")]'
    },
    firstNameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="fname"]'
    },
    lastNameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="lname"]'
    },
    registerUserBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//*[@id="pwd-container"]/div/section/form/div/button[1]'
    }
}

export class RegisterAssociatePage extends BasePage {
    loginPage = new LoginPage();
    registerBtn = this.ElementLocator(Locators.registerBtn);
    usernameInput = this.ElementLocator(Locators.usernameInput);
    passwordInput = this.ElementLocator(Locators.passwordInput);
    cPasswordInput = this.ElementLocator(Locators.cPasswordInput);
    nextPageBtn = this.ElementLocator(Locators.nextPageBtn);
    firstNameInput = this.ElementLocator(Locators.firstNameInput);
    lastNameInput = this.ElementLocator(Locators.lastNameInput);
    registerUserBtn = this.ElementLocator(Locators.registerUserBtn);

    navigateToLogin() {
        this.loginPage.navigateTo();
    }

    RegisterBtn() {
        return this.registerBtn;
    }

    AssociateUsername() {
        return this.usernameInput;
    }

    AssociatePassword() {
      return this.passwordInput;
    }

    AssociateCPassword() {
      return this.cPasswordInput;
    }

    clickNextPageBtn() {
        this.nextPageBtn;
    }

    FirstName() {
        return this.firstNameInput;
    }

    LastName() {
        return this.lastNameInput;
    }

    RolePick() {
      return browser.findElement(by.cssContainingText('option', 'Associate'));
    }

    RegisterUserBtn() {
        return this.registerUserBtn;
    }

}
