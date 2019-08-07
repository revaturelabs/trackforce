import { UploadTabPage } from './uploadReport.po';
import { AppPage } from './../app.po';
import { browser, by, element } from 'protractor';


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

  it('should be able to enter a start date', () => {
    UploadTab.selectstartdate().sendKeys('08/03/2019');
    expect(UploadTab.getstardate().getAttribute('value')).not.toBe('');
  });

  it('should be able to enter a end date', () => {
    UploadTab.selectendtdate().sendKeys('08/06/2019');
    expect(UploadTab.selectendtdate().getAttribute('value')).not.toBe('');
  });

  it('should be able to enter completedsp number', () => {
    UploadTab.entercompletedsp().sendKeys('2');
    expect(UploadTab.entercompletedsp().getAttribute('value')).not.toBe('');
  });

  it('should be able to enter assignedsp number', () => {
    UploadTab.enterassignedsp().sendKeys('2');
    expect(UploadTab.enterassignedsp().getAttribute('value')).not.toBe('');
  });

  it('should be able to select trackforce in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojecttrackforce().click();
    expect(UploadTab.selectprojectbtn().getText()).toBe('Trackforce');
  });

  it('should be able to select rideforce in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectrideforce().click();
    expect(UploadTab.selectprojectbtn().getText()).toBe('Rideforce');
  });

  it('should be able to select sms in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectsms().click();
    expect(UploadTab.selectprojectbtn().getText()).toBe('SMS');
  });

  it('should be able to select cms in project ', () => {
    UploadTab.selectprojectbtn().click();
    UploadTab.selectprojectcms().click();
    expect(UploadTab.selectprojectbtn().getText()).toBe('CMS');
  });

  it('should be able to enter an iteration name ', () => {
    UploadTab.enterprojectiteration().click();
    UploadTab.enterprojectiteration().sendKeys('FirstIteration');
    expect(UploadTab.enterprojectiteration().getText()).not.toEqual(0);
  });

  // the input box will always be empty (reasons to why we are checking emptiness)
  it('should be able to select a file from testfile folder in asset ', () => {
    const filepath = process.cwd() + '\\src\\assets\\testFiles.\\file.txt';
    UploadTab.uploadafile().sendKeys(filepath);
    browser.sleep(5000);
    expect(UploadTab.getuploadedfile().isDisplayed()).toBeTruthy();
  });

  it('should be able to send the report by clicking submit button ', () => {
    UploadTab.sendreport().click();
    browser.sleep(5000);
    expect(UploadTab.getuploadedfile().isPresent()).toBe(false);
  });

});
