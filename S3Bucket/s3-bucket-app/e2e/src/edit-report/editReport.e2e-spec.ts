import { EditReport } from './editReport.po';
import { AppPage } from './../app.po';

describe('A user can able to edit previous reports', () => {

  const appPage = new AppPage();
  const editReport = new EditReport();

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be navigate to the edit tab', () => {
    editReport.getEditReportTab().click();
    expect(editReport.getProjectListSelector()).toBeTruthy();
  });


});
