import { BasePage, IdentificationType } from '../base.po';

const Locators = {
  editReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[3]/a'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div/div[1]/div[2]/div/div[1]/button'
  }
};

export class EditReport extends BasePage {

  editReportTab = this.ElementLocator(Locators.editReportTab);
  projectListSelector = this.ElementLocator(Locators.projectListSelector);

  getEditReportTab(){
    return this.editReportTab;
  }

  getProjectListSelector() {
    return this.projectListSelector;
  }
}
