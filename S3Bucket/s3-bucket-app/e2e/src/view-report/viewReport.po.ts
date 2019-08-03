import { BasePage, IdentificationType } from './../base.po';

const Locators = {
  viewReportTab: {
  type: IdentificationType[IdentificationType.Xpath],
  value: '//*[@id="pwd-container"]/div/section/form/div/div[2]/button[2]'
}
}
export class ViewReport extends BasePage {

}
