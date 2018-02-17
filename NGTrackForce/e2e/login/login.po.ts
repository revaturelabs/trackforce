import { browser, by, element } from 'protractor';

export class LoginPage {
  navigateTo(){
    return browser.get('/');
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
    return element(by.xpath('//button'));
	//return element(by.xpath("//button[@class='btn btn-lg btn-primary btn-block']"));
  }

  getLoginButtonText(){
    return element(by.xpath('//button')).getText();
  }

  getTitle(){
    return element(by.xpath('//title')).getText();
  }
}
