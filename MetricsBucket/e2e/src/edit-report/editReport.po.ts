import { BasePage, IdentificationType } from '../base.po';

const Locators = {
  editReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[3]/a'
  },
  projectBtn: {
    type: IdentificationType[IdentificationType.Css],
    value: '#edit-reports > app-edit-reports > div.row.mt-4 > div > div > div > button'
  },
  firstProject: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div[1]/div/div/div[1]/div/div[1]/p'
  },
  itterationBtn: {
    type: IdentificationType[IdentificationType.Css],
    value: '#edit-reports > app-edit-reports > div.row.mt-4 > div > div > div.input-group-append.ml-2 > button'
  },
  firstItteration: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div[1]/div/div/div[2]/div/div/p'
  },
  addFilesInputBox: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '  //*[@id="edit-reports"]/app-edit-reports/div[2]/div/div/button[1]/input'
  },
  updateBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="update"]'
  },
};

export class EditReport extends BasePage {

  editReportTab = this.ElementLocator(Locators.editReportTab);
  projectBtn = this.ElementLocator(Locators.projectBtn);
  firstProject = this.ElementLocator(Locators.firstProject);
  itterationBtn = this.ElementLocator(Locators.itterationBtn);
  firstItteration = this.ElementLocator(Locators.firstItteration);
  addFilesInputBox = this.ElementLocator(Locators.addFilesInputBox);
  updateBtn = this.ElementLocator(Locators.updateBtn);

  getEditReportTab() {
    return this.editReportTab;
  }

  getProjectBtn() {
    return this.projectBtn;
  }

  getFirstProject() {
    return this.firstProject;
  }

  getItterationBtn() {
    return this.itterationBtn;
  }

  getFirstItteration() {
    return this.firstItteration;
  }

  getAddFilesInputBox() {
    return this.addFilesInputBox;
  }

  getUpdateBtn() {
    return this.updateBtn;
  }

}
