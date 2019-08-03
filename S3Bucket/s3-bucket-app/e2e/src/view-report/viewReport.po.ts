import { BasePage, IdentificationType } from './../base.po';

const Locators = {
  viewReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[1]/a'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Id],
    value: 'projectChoice';
  }
};
export class ViewReport extends BasePage {

  viewReportTab = this.ElementLocator(Locators.viewReportTab);

  ViewReportTab() {
    return this.viewReportTab;
  }

  ProjectListSelector(){
    return this.ProjectListSelector;
  }



}
