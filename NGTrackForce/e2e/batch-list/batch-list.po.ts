import { browser, by, element } from 'protractor';

export class BatchList {
  navigateTo() {
    return browser.get('/');
  }

  getUsername() {
    return element(by.name('username')).getAttribute("value");
  }

  getPassword() {
    return element(by.name('password')).getAttribute("value");
  }

  getPieChart() {
    return element(by.id('pie'));
  }
}
