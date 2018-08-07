import { BasePage, IdentificationType } from "../BasePage";
import { element, by } from "protractor";

const Locators = {
    predictionsTab: {
        type: IdentificationType[IdentificationType.Css],
        // value: '[routerlink="/predictions"]'
        value: '//div[@class="container-fluid"]/ul/li[7]'
    },
    startDate: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="startDate"]'
    },
    endDate: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="endDate"]'
    },
    numberOfAssociates: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@name="number"]'
    },
    overSelect: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//div[@class="overSelect"]'
    },
    inputOne: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="tech1"]'
    },
    inputTwo: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//input[@id="tech2"]'
    },
    getPredictionButton: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[@type="button"]'
    },
    predictionsTable: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//table'
    },
    predictionTableEntries: {
        type: IdentificationType[IdentificationType.Tag],
        value: 'tr'
    },
    numberOfAssociatesInTableEntry: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//tbody/tr[1]/td[3]'
    },
    differenceInTableEntry: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//tbody/tr[1]/td[4]'
    },
    predictionsTableHead: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//thead'
    },
    detailsBtn: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//button[@name="getDetailsBtn"]'
    },
    predictionBreakdownHeader: {
        type: IdentificationType[IdentificationType.Xpath],
        value: '//h3[contains(text(), "reakdown")]'
    }
}

export class PredictionsPage extends BasePage {
    predictionsTag = this.ElementLocator(Locators.predictionsTab);
    startDate = this.ElementLocator(Locators.startDate);
    endDate = this.ElementLocator(Locators.endDate);
    numberOfAssociates = this.ElementLocator(Locators.numberOfAssociates);
    overSelect = this.ElementLocator(Locators.overSelect);
    inputOne = this.ElementLocator(Locators.inputOne);
    inputTwo = this.ElementLocator(Locators.inputTwo);
    getPredictionButton = this.ElementLocator(Locators.getPredictionButton);
    predictionsTable = this.ElementLocator(Locators.predictionsTable);
    numberOfAssociatesInTableEntry = this.ElementLocator(Locators.numberOfAssociatesInTableEntry);
    differenceInTableEntry = this.ElementLocator(Locators.differenceInTableEntry);
    predictionsTableEntries = this.ElementLocator(Locators.predictionTableEntries);
    predictionsTableHead = this.ElementLocator(Locators.predictionsTableHead);
    detailsBtn = this.ElementLocator(Locators.detailsBtn);
    predictionBreakdownHeader = this.ElementLocator(Locators.predictionBreakdownHeader);

    navigateTo() {
        this.predictionsTag.click();
    }

    inputStartDate(input: string) {
        this.startDate.sendKeys(input);
    }

    inputEndDate(input: string) {
        this.endDate.sendKeys(input);
    }

    inputNumAssociates(input: string) {
        this.numberOfAssociates.sendKeys(input);
    }

    filterByTechnologies() {
        this.overSelect.click();
        this.inputOne.click();
        this.inputTwo.click();
        this.getPredictionButton.click();
    }

    getPredictionsTable() {
        return this.predictionsTable;
    }

    getPredictionsTableEntriesCount() {
        return this.predictionsTableEntries.count();
    }

    getPredictionsTableHead() {
        return this.predictionsTableHead;
    }

    getPredictionsBreakdownHeader() {
        return this.predictionBreakdownHeader;
    }

    checkFirstNineBoxes() {
        this.overSelect.click();
        for (let i = 1 ; i <= 9 ; i++) {
            let path = `//input[@id="tech${i}"]`
            element(by.xpath(path)).click();
        }
        this.getPredictionButton.click();
    }


}