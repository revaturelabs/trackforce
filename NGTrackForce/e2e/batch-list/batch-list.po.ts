import { browser, by, element } from 'protractor';

export class BatchList {
  getBatchListTable(){
    return element(by.xpath('/html/body/app-component/div/app-batch-list/div/div[2]/div[1]/table'));
  }
}
