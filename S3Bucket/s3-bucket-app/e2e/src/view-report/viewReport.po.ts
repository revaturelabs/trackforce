import { BasePage, IdentificationType } from './../base.po';

const Locators = {
  viewReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[1]/a'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Id],
    value: 'projectChoice'
  }
};
export class ViewReport extends BasePage {

  viewReportTab = this.ElementLocator(Locators.viewReportTab);
  projectListSelector = this.ElementLocator(Locators.projectListSelector);

  getViewReportTab() {
    return this.viewReportTab;
  }

  getProjectListSelector() {
    return this.projectListSelector;
  }



}
