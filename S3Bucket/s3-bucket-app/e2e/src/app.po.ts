import { TestConfig } from './configuration/test-config';
import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(TestConfig.baseURL) as Promise<any>;
  }

  getTitleText() {
    return browser.getTitle() as Promise<string>;
  }
}
