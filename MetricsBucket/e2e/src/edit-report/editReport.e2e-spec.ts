import { EditReport } from './editReport.po';
import { AppPage } from './../app.po';
import { browser } from 'protractor';

describe('A user can able to edit previous reports', () => {

  const appPage = new AppPage();
  const editReport = new EditReport();

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be navigate to the edit tab', () => {
    editReport.getEditReportTab().click();
    browser.sleep(2000);
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
    const fileName = 'file.txt';
    const filepath = process.cwd() + '\\src\\assets\\testFiles.\\' + fileName;
    editReport.getAddFilesInputBox().sendKeys(filepath);
    editReport.getUpdateBtn().click();
    browser.sleep(2000);
    editReport.getProjectBtn().click();
    editReport.getFirstProject().click();
    editReport.getItterationBtn().click();
    editReport.getFirstItteration().click();
    editReport.getLastFile().getText().then((f) => {
      expect(f.toString().replace('[', '').replace(']', '')).toBe(fileName);
    });
  });

  it('should be able to remove files', () => {
    const fileName = 'file.txt';
    editReport.getLastFileRemoveBtn().click();
    editReport.getUpdateBtn().click();
    browser.sleep(2000);
    editReport.getProjectBtn().click();
    editReport.getFirstProject().click();
    editReport.getItterationBtn().click();
    editReport.getFirstItteration().click();
    editReport.getLastFile().getText().then((f) => {
      expect(f.toString().replace('[', '').replace(']', '')).not.toBe(fileName);
    });
  });

  it('should be able to delete iterators', () => {
    editReport.getRemoveIterationBtn().click();
    browser.sleep(2000);
    editReport.getremoveIterationConfirmBtn().click();
    browser.sleep(2000);
  });
});
