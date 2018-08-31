import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  getSelectDatesHeader() {
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/h3'));
  }
}
