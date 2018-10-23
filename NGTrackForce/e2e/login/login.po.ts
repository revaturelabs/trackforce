import { browser, by, element } from 'protractor';

export class LoginPage {
  navigateTo(){
    return browser.get('./login');
    //return browser.get('/login');
  }

  getUsernameInput(){
    return element(by.name('username'));
  }

  getUsernamePlaceholder(){
    return element(by.name('username')).getAttribute('placeholder');
  }

  getPasswordInput(){
    return element(by.name('password'));
  }

  getPasswordPlaceholder(){
    return element(by.name('password')).getAttribute('placeholder');
  }

  getLoginButton(){
    return element(by.buttonText('SIGN IN'));
  }

  getLoginButtonText(){
    return element(by.xpath('//button')).getText();
  }

  getTitle(){
    return element(by.xpath('//title')).getText();
  }

  getFailedLoginResponse() {
    return element(by.xpath('//*[@id="pwd-container"]/div/section/form/div/div[1]/span')).getText();
    //
  }

  getlogoutButton() {
    element(by.xpath('//*[@id="navbarDropdown"]')).click();
    return element(by.xpath('//*[@id="logout"]'));
  }

  getCurrentUrl(){
    return browser.getCurrentUrl();
  }
}
