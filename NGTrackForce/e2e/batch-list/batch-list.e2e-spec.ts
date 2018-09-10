import { BatchList } from './batch-list.po';
import { Navbar } from '../navbar/navbar.po';
import { TestConfig } from "../configuration/test-config";

describe('the bactch-list ', () => {
  let navbar          : Navbar;
  let testConfig      : TestConfig;
  let batchlist       : BatchList;
  let baseURL         : string;

  beforeAll(() => {
      navbar = new Navbar();
      testConfig = new TestConfig();
      batchlist = new BatchList();
      baseURL = testConfig.getBaseURL();
      navbar.navigateTo();
      navbar.logIn("TestAdmin","TestAdmin");
  });

  it('should have a batch list table', () => {
    expect(batchlist.getBatchListTable()).toBeTruthy();
  });

});
