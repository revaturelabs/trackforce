import { browser, by, element } from 'protractor';

export class LoginPage {
  navigateTo(){
    return browser.get('./#/login');
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
    return element(by.buttonText('Sign in'));
  }

  getLoginButtonText(){
    return element(by.xpath('//button')).getText();
  }

  getTitle(){
    return element(by.xpath('//title')).getText();
  }

  getFailedLoginResponse() {
    return element(by.xpath('//*[@id="pwd-container"]/div/section/form/div[1]')).getText();
  }

  getlogoutButton() {
    return element(by.xpath('/html/body/app-component/app-navbar/nav/div/ul[1]/li[2]/button'));
  }
  getUrl(){
    return browser.baseUrl;
  }
}
