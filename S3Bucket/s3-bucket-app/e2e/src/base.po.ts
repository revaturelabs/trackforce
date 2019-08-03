import { element, by } from 'protractor';

export enum IdentificationType {
  Xpath,
  ElementsByXpath,
  Css,
  Id,
  Js,
  Name,
  LinkText,
  PartialLinkText,
  ClassName,
  Tag
}

export class BasePage {
  ElementLocator(obj) {
    switch (obj.type) {
      case IdentificationType[IdentificationType.Xpath]:
        return element(by.xpath(obj.value));
      case IdentificationType[IdentificationType.ElementsByXpath]:
        return element.all(by.xpath(obj.value));
      case IdentificationType[IdentificationType.ClassName]:
        return element(by.className(obj.value));
      case IdentificationType[IdentificationType.Id]:
        return element(by.id(obj.value));
      case IdentificationType[IdentificationType.Js]:
        return element(by.js(obj.value));
      case IdentificationType[IdentificationType.Css]:
        return element(by.css(obj.value));
      case IdentificationType[IdentificationType.Name]:
        return element(by.name(obj.value));
      case IdentificationType[IdentificationType.Tag]:
        return element.all(by.tagName(obj.value));
      case IdentificationType[IdentificationType.LinkText]:
        return element.all(by.linkText(obj.value));
      case IdentificationType[IdentificationType.PartialLinkText]:
        return element.all(by.partialLinkText(obj.value));
      default:
        break;
    }
  }

}
