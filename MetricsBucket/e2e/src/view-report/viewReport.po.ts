import { BasePage, IdentificationType } from './../base.po';
import { browser, by, element } from 'protractor';

const Locators = {
  viewReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/ul/li[1]/a/h3'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[1]/app-view-reports/div/div/div/div/button'
  },
  cmsOption: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[1]/app-view-reports/div/div/div/div/div/div[1]/p'
  },
  selectedIteration: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[1]/app-view-reports/div/div/div/div[2]/button'
  },
  youAreWrongOption: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[1]/app-view-reports/div[1]/div/div/div[2]/div/div/p'
  },
  iterationView: {
    type: IdentificationType[IdentificationType.Id],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[1]/app-view-reports/div/div/div/div[2]/div'
  },
};
export class ViewReport extends BasePage {

  viewReportTab = this.ElementLocator(Locators.viewReportTab);
  projectListSelector = this.ElementLocator(Locators.projectListSelector);
  cmsOption = this.ElementLocator(Locators.cmsOption);
  selectedIteration = this.ElementLocator(Locators.selectedIteration);
  youAreWrongOption = this.ElementLocator(Locators.youAreWrongOption);
  iterationView = this.ElementLocator(Locators.iterationView);

  ViewReportTab() {
    browser.sleep(2000);
    return this.viewReportTab;
  }
  ProjectListSelector() {
    browser.sleep(2000);
    return this.projectListSelector;
  }
  CmsOption() {
    browser.sleep(2000);
    return this.cmsOption;
  }
  SelectedIteration() {
    browser.sleep(2000);
    return this.selectedIteration;
  }
  YouAreWrongOption() {
    browser.sleep(2000);
    return this.youAreWrongOption;
  }
  IterationView() {
    browser.sleep(2000);
    return this.iterationView;
  }
}
