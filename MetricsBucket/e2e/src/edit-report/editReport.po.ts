import { BasePage, IdentificationType } from '../base.po';
import { by } from 'protractor';

const Locators = {
  editReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[3]/a'
  },
  projectBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="project"]'
  },
  firstProject: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div[1]/div/div/div[1]/div/div[1]/p'
  },
  itterationBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="iterations"]'
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
  allFiles: {
    type: IdentificationType[IdentificationType.Css],
    value: '#edit-reports > app-edit-reports > div:nth-child(2) > div > ul'
  },
  removeIterationBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="remove"]'
  },
  removeIterationConfirmBtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="edit-reports"]/app-edit-reports/div[3]/div/div/button[1]'
  }
};

export class EditReport extends BasePage {

  editReportTab = this.ElementLocator(Locators.editReportTab);
  projectBtn = this.ElementLocator(Locators.projectBtn);
  firstProject = this.ElementLocator(Locators.firstProject);
  itterationBtn = this.ElementLocator(Locators.itterationBtn);
  firstItteration = this.ElementLocator(Locators.firstItteration);
  addFilesInputBox = this.ElementLocator(Locators.addFilesInputBox);
  updateBtn = this.ElementLocator(Locators.updateBtn);
  allFiles = this.ElementsArrayLocator(Locators.allFiles);
  removeIterationBtn = this.ElementLocator(Locators.removeIterationBtn);
  removeIterationConfirmBtn = this.ElementLocator(Locators.removeIterationConfirmBtn);

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

  getLastFile() {
    return this.allFiles.last().all(by.css('.fileNames'));
  }

  getLastFileRemoveBtn() {
    return this.allFiles.last().all(by.id('remove'));
  }

  getRemoveIterationBtn() {
    return this.removeIterationBtn;
  }

  getremoveIterationConfirmBtn() {
    return this.removeIterationConfirmBtn;
  }

}
