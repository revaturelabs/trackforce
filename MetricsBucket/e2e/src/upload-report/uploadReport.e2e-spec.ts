import { UploadTabPage } from './uploadReport.po';
import { AppPage } from './../app.po';


describe('A user is able to upload file to s3 bucket', () => {

  const appPage = new AppPage();
  const UploadTab = new UploadTabPage();

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be able to naviage to upload tab', () => {
    UploadTab.navigateuploadtab().click();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

//need some work
  it('should be able to enter a start date', () => {
    UploadTab.selectstartdate();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to enter a end date', () => {
    UploadTab.selectendtdate();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to enter completedsp number', () => {
    UploadTab.entercompletedsp();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to enter assignedsp number', () => {
    UploadTab.enterassignedsp();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to select trackforce in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojecttrackforce().click();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to select rideforce in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectrideforce().click();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to select sms in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectsms().click();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

  it('should be able to select cms in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectcms().click();
    expect(UploadTab.getstardate()).toBeTruthy();
  });

});
