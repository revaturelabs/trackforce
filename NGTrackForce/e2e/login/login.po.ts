import { browser, by, element } from 'protractor';

export class LoginPage {
  navigateTo(){
    return browser.get('/login');
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
    return element(by.buttonText('Sign in'));
  }

  getLoginButtonText(){
    return element(by.xpath('//button')).getText();
  }

  getTitle(){
    return element(by.xpath('//title')).getText();
  }

  getFailedLoginResponse() {
    return element(by.xpath('//*[@id="pwd-container"]/div[2]/section/form/div[1]')).getText();
  }

  get logoutButton() {
    return element(by.css('a[href*="/login"]'));
  }
}
