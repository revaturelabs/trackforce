"use strict";
exports.__esModule = true;
var protractor_1 = require("protractor");
var LoginPage = /** @class */ (function () {
    function LoginPage() {
    }
    LoginPage.prototype.navigateTo = function () {
        return protractor_1.browser.get('/NGTrackForce');
        //return browser.get('/login');
    };
    LoginPage.prototype.getUsernameInput = function () {
        return protractor_1.element(protractor_1.by.name('username'));
    };
    LoginPage.prototype.getUsernamePlaceholder = function () {
        return protractor_1.element(protractor_1.by.name('username')).getAttribute('placeholder');
    };
    LoginPage.prototype.getPasswordInput = function () {
        return protractor_1.element(protractor_1.by.name('password'));
    };
    LoginPage.prototype.getPasswordPlaceholder = function () {
        return protractor_1.element(protractor_1.by.name('password')).getAttribute('placeholder');
    };
    LoginPage.prototype.getLoginButton = function () {
        return protractor_1.element(protractor_1.by.buttonText('Sign in'));
    };
    LoginPage.prototype.getLoginButtonText = function () {
        return protractor_1.element(protractor_1.by.xpath('//button')).getText();
    };
    LoginPage.prototype.getTitle = function () {
        return protractor_1.element(protractor_1.by.xpath('//title')).getText();
    };
    LoginPage.prototype.getFailedLoginResponse = function () {
        return protractor_1.element(protractor_1.by.xpath('//*[@id="pwd-container"]/div/section/form/div[1]')).getText();
    };
    Object.defineProperty(LoginPage.prototype, "logoutButton", {
        get: function () {
            return protractor_1.element(protractor_1.by.css('a[href*="/login"]'));
        },
        enumerable: true,
        configurable: true
    });
    return LoginPage;
}());
exports.LoginPage = LoginPage;
