import { BasePage, IdentificationType } from "../BasePage";
import { LoginPage } from "../login/login.po";

const Locators = {
    registerBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[contains(text(), "egister")]'
    },
    usernameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="username"]'
    },
    passwordInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="password"]'
    },
    cPasswordInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="cpassword"]'
    },
    nextPageBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[contains(text(), "ext")]'
    },
    firstNameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="fname"]'
    },
    lastNameInput: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="lname"]'
    },
    registerUserBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[contains(text(), "egister")]'
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

    clickRegisterBtn() {
        this.registerBtn.click();
    }

    enterAssociateInfo(username: string, password: string) {
        this.usernameInput.sendKeys(username);
        this.passwordInput.sendKeys(password);
        this.cPasswordInput.sendKeys(password);
    }

    clickNextPageBtn() {
        this.nextPageBtn.click();
    }

    enterFirstName(first: string) {
        this.firstNameInput.sendKeys(first);
    }

    enterLastName(last: string) {
        this.lastNameInput.sendKeys(last);
    }

    clickRegisterUserBtn() {
        this.registerUserBtn.click();
    }

}