import { BasePage, IdentificationType } from './../base.po';

const Locators = {
  viewReportTab: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="bucket-container"]/ul/li[1]/a'
  },
  projectListSelector: {
    type: IdentificationType[IdentificationType.Id],
    value: 'projectChoice'
  },
  cmsOption: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="projectChoice"]/option[1]'
  },
  selectedIteration: {
    type: IdentificationType[IdentificationType.Id],
    value: 'selectedIteration'
  },
  youAreWrongOption: {
    type: IdentificationType[IdentificationType.Xpath],
    value: '//*[@id="selectedIteration"]/option[1]'
  },
};
export class ViewReport extends BasePage {

  viewReportTab = this.ElementLocator(Locators.viewReportTab);
  projectListSelector = this.ElementLocator(Locators.projectListSelector);
  cmsOption = this.ElementLocator(Locators.cmsOption);
  selectedIteration = this.ElementLocator(Locators.selectedIteration);

  ViewReportTab() {
    return this.viewReportTab;
  }
  ProjectListSelector() {
    return this.projectListSelector;
  }
  CmsOption() {
    return this.cmsOption;
  }
  SelectedIteration() {
    return this.selectedIteration;
  }
}
