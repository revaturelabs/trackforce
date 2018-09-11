import { browser, by, element } from 'protractor';

export class BatchList {
  getBatchListTable(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div/div[1]/table'));
  }
  getFirstCalander(){
    return element(by.id('startDate'));
  }
  getSecondCalander(){
    return element(by.id('endDate'));
  }
  getPieCart(){
    return element(by.id('pie'));
  }
  getPieChartPlaceHolder(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/h3[2]'));
  }
  getBatch(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div/div[1]/table/tbody/tr[1]/td[1]/a'));
  }
  getCurrentUrl(){
    return browser.getCurrentUrl();
  }
  getBatchCart(){
    return element(by.id('bar'));
  }
  getBatchTable(){
    return element(by.xpath('/html/body/app-component/div/app-batch-details/div/div/div[2]/table'))
  }
}
