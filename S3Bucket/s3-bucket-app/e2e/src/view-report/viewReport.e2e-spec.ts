import { ViewReport } from './viewReport.po';
import { AppPage } from './../app.po';

describe('A user is able to view previous reports', () => {

  const appPage = new AppPage();
  const viewReport = new ViewReport();

  beforeAll(() => {
    appPage.navigateTo();
  });

  it('should be navigate to the view tab', () => {
    viewReport.ViewReportTab().click();
    // expect(viewReport.ProjectListSelector()).toBeTruthy();
    viewReport.ProjectListSelector().click();
    viewReport.CmsOption().click();
    expect(viewReport.SelectedIteration()).toBeTruthy();
    viewReport.SelectedIteration().click();
    viewReport.YouAreWrongOption().click();
    expect(viewReport.IterationView()).toBeTruthy();
  });


});
