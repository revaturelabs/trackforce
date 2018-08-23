import { BasePage, IdentificationType } from "../BasePage";
import { element, by } from "protractor";

export class PredictionsPage extends BasePage {
    navigateTo() {
      element(by.linkText('Predictions')).click();
    }

    inputStartDate(input: string) {
      element(by.name('startDate')).sendKeys(input);
    }

    inputEndDate(input: string) {
      element(by.name('endDate')).sendKeys(input);
    }

    getTechnologyCount() {
      return element(by.id('techTable')).all(by.css('tbody tr')).count();
    }

    getTechnology(index: number) {
      return element(by.id('techTable')).all(by.css('tbody tr')).get(index).getText();
    }

    inputTechCount(index: number, count: number) {
      this.getTechnologyCount().then( res => {
        if (index >= 0 && index < res) {
          this.getTechnology(index).then(tech => {
            element(by.id(tech+'-input')).click();
            element(by.id(tech+'-input')).sendKeys(count);
          });
        }
      });
    }

    clearInputCount() {
      this.getTechnologyCount().then( count => {
        for(let i = 0; i < count; ++i) {
          this.getTechnology(i).then(tech => {
            element(by.id(tech+'-input')).clear();
          });
        }
      });
    }

    checkAssociateCount(tech: string) {
      expect(element(by.id(tech))).toBeTruthy();
    }

    getPredictionsTable() {
      return element(by.id('predictionsTable'));
    }

    getPredictionsTableRows() {
      return element(by.id('predictionsTable')).all(by.css('tbody tr')).count();
    }

    clickPredictionRow(row: number) {

    }

    getPredictionDetailsHeader() {
      return element(by.id('batchDetailsHeader'));
    }

    outOfFocus() {
      element(by.id('predictionsHeader')).click();
    }

}
