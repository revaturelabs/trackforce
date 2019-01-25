import { protractor, browser, by, element } from 'protractor';
import { environment } from '../../src/environments/environment';

export class LoginPage { 
  navigateTo(){
    return browser.get(environment.url + 'TrackForce/login');
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
    let ec = protractor.ExpectedConditions;
    browser.wait(ec.visibilityOf(element(by.id('logo'))), 5000);  //waits until the logo element is loaded to check url -CR
    return browser.getCurrentUrl();
  }
}
