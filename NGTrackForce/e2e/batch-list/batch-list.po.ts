import { browser, by, element } from 'protractor';

export class BatchList {
  getBatchListTable(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[1]/table'));
  }
  getFirstCalander(){
    return element(by.id('startDate'));
  }
  getSecondCalander(){
    return element(by.id('endDate'));
  }
  getSubmitBtn(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/form/div[3]/input[1]'))
  }
  getResetBtn(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/form/div[3]/input[2]'))
  }
  getPieCart(){
    return element(by.id('pie'));
  }
  getPieChartPlaceHolder(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[2]/h3[2]'));
  }
}
