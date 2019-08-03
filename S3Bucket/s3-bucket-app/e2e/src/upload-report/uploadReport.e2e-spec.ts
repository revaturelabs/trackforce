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


  it('should be able to select a start date', () => {

  });

});
