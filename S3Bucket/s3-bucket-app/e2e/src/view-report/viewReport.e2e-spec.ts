import { ViewReport } from './viewReport.po';
import { AppPage } from './../app.po';

describe('A user can able to view previous reports', () => {

  const appPage = new AppPage();
  const viewReport = new ViewReport();

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be navigate to the view tab', () => {
    viewReport.getViewReportTab().click();
    expect(viewReport.getProjectListSelector()).toBeTruthy();
  });


});
