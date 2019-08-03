import { BasePage, IdentificationType } from './../base.po';
import { browser, by, element } from 'protractor';

const Locators = {
  uploadtab: {
      type: IdentificationType[IdentificationType.Xpath],
      value: '/html/body/app-root/app-sprint-reports/div/div[2]/ul/li[2]/a/h3'
  },

  stardate: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="start-date"]'
  },

  enddate: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="end-date"]'
  },

  completedsp: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="completed"]'
  },

  assignedsp: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="assigned"]'
  },

  project: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/div[1]/button'
  },

  project_trackforce: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/p[1]'
  },

  project_rideforce: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/p[2]'
  },

  project_sms: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/p[3]'
  },

  project_cms: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/div[1]/div/p[4]'
  },

  project_iteration: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/div[2]/div/div/input'
  },

  browsebtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="upload"]'
  },

  submitbtn: {
    type: IdentificationType[IdentificationType.Xpath],
    value: ''
  }
};

export class UploadTabPage extends BasePage {
  uploadtab = this.ElementLocator(Locators.uploadtab);
  stardate = this.ElementLocator(Locators.stardate);

  navigateuploadtab() {
    return this.uploadtab;
  }

  getstardate() {
    return this.stardate;
  }

  selectstartdate(){
    return this.stardate;
  }

}

