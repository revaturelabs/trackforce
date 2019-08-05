import { BasePage, IdentificationType } from './../base.po';
import { browser, by, element } from 'protractor';
import { Driver } from 'selenium-webdriver/edge';

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
    value: '//*[@id="projectbtn"]'
  },

  project_trackforce: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/app-upload-reports/div/div[2]/div/div/div[1]/div/div[4]/p'
  },

  project_rideforce: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/app-upload-reports/div/div[2]/div/div/div[1]/div/div[2]/p'
  },

  project_sms: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/app-upload-reports/div/div[2]/div/div/div[1]/div/div[3]/p'
  },

  project_cms: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '/html/body/app-root/app-sprint-reports/div/div[2]/div/div[2]/app-upload-reports/div/div[2]/div/div/div[1]/div/div[1]/p'
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
  enddate = this.ElementLocator(Locators.enddate);
  completedsp = this.ElementLocator(Locators.completedsp);
  assignedsp = this.ElementLocator(Locators.assignedsp);

  project  = this.ElementLocator(Locators.project);
  project_trackforce  = this.ElementLocator(Locators.project_trackforce);
  project_rideforce = this.ElementLocator(Locators.project_rideforce);
  project_sms = this.ElementLocator(Locators.project_sms);
  project_cms = this.ElementLocator(Locators.project_cms);

  navigateuploadtab() {
    return this.uploadtab;
  }

  getstardate() {
    return this.stardate;
  }

  selectstartdate(){
    browser.sleep(1000);
    return this.stardate.sendKeys('08/03/2019');
  }

  selectendtdate(){
    browser.sleep(1000);
    return this.enddate.sendKeys('08/06/2019');
  }

  entercompletedsp(){
    browser.sleep(1000);
    return this.completedsp.sendKeys('2');
  }

  enterassignedsp(){
    browser.sleep(1000);
    return this.completedsp.sendKeys('2');
  }

  selectprojectbtn(){
    browser.sleep(1000);
    return this.project;
  }

  selectprojecttrackforce(){
    browser.sleep(1000);
    return this.project_trackforce;
  }

  selectprojectrideforce(){
    browser.sleep(1000);
    return this.project_rideforce;
  }

  selectprojectsms(){
    browser.sleep(1000);
    return this.project_sms;
  }

  selectprojectcms(){
    browser.sleep(1000);
    return this.project_cms;
  }

}

