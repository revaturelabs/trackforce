import { EditReport } from './editReport.po';
import { AppPage } from './../app.po';
import { browser } from 'protractor';

describe('A user can able to edit previous reports', () => {

  const appPage = new AppPage();
  const editReport = new EditReport();
  let path = require('path');

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be navigate to the edit tab', () => {
    editReport.getEditReportTab().click();
    expect(editReport.getProjectBtn()).toBeTruthy();
  });

  it('should be able select first project', () => {
    editReport.getProjectBtn().click();
    const firstElementText = editReport.getFirstProject().getText();
    editReport.getFirstProject().click();
    expect(editReport.getProjectBtn().getText()).toBe(firstElementText);
  });

  it('should be able select first itteration', () => {
    editReport.getItterationBtn().click();
    const firstIterationText = editReport.getFirstItteration().getText();
    editReport.getFirstItteration().click();
    expect(editReport.getItterationBtn().getText()).toBe(firstIterationText);
  });

  it('should be able to upload new files', () => {
    const filepath = process.cwd() + '\\src\\assets\\testFiles.\\file.txt';
    editReport.getAddFilesInputBox().sendKeys(filepath);
    browser.sleep(2000);
    editReport.getUpdateBtn().click();
  });
});
