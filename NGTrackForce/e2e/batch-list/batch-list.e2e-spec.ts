import { BatchList } from './batch-list.po';
import { Navbar } from "../navbar/navbar.po";
import { LoginPage } from '../login/login.po';
import { TestConfig } from "../configuration/test-config";

describe('the bactch-list ', () => {
  let navbar          : Navbar;
  let page            : LoginPage;
  let testConfig      : TestConfig;
  let batchlist       : BatchList;
  let baseURL         : string;

  beforeAll(() => {
      navbar = new Navbar();
      page = new LoginPage();
      testConfig = new TestConfig();
      batchlist = new BatchList();
      baseURL = testConfig.getBaseURL();
      page.navigateTo();
      navbar.logIn("TestAdmin","TestAdmin");
  });

  it('should have a batch list table', () => {
    navbar.goToBatchList();
    expect(batchlist.getBatchListTable()).toBeTruthy();
  });

  afterAll(() => {
    page.getlogoutButton().click();
  });

});
