import { BasePage, IdentificationType } from '../base.po';

const Locators = {
  editReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[3]/a'
  },
  projectBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div/div[1]/div[2]/div/div[1]/button'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Css],
    value: '#edit-reports > app-edit-reports > div > div.row.mt-4 > div.col > div > div.input-group-prepend.show > div > div:nth-child(1)'
  },
  firstProject: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div/div[1]/div[2]/div/div[1]/div/div[1]/p'
  }
};

export class EditReport extends BasePage {

  editReportTab = this.ElementLocator(Locators.editReportTab);
  projectBtn = this.ElementLocator(Locators.projectBtn);
  projectListSelector = this.ElementsArrayLocator(Locators.projectListSelector);
  firstProject = this.ElementLocator(Locators.firstProject);

  getEditReportTab() {
    return this.editReportTab;
  }

  getProjectBtn() {
    return this.projectBtn;
  }

  getFirstElementInProjectSelector() {
    return this.projectListSelector.first();
  }

  getFirstProject() {
    return this.firstProject;
  }
}
