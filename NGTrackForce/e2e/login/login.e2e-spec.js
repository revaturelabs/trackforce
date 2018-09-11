"use strict";
exports.__esModule = true;
var login_po_1 = require("./login.po");
/*
Smoke test: Checks elements to be exist
*/
describe('login page element existences', function () {
    var page;
    beforeAll(function () {
        page = new login_po_1.LoginPage();
        page.navigateTo();
    });
    it('should have a username input box', function () {
        expect(page.getUsernameInput()).toBeTruthy();
    });
    it('should have a password input box', function () {
        expect(page.getPasswordInput()).toBeTruthy();
    });
    it('should have a login button', function () {
        expect(page.getLoginButton()).toBeTruthy();
    });
    it('should display proper placeholder: Username', function () {
        expect(page.getUsernamePlaceholder()).toEqual('Username');
    });
    it('should display proper placeholder: Password', function () {
        expect(page.getPasswordPlaceholder()).toEqual('Password');
    });
    it('should display proper value for button', function () {
        expect(page.getLoginButtonText()).toEqual('Sign in');
    });
});
describe('Confirm login failures', function () {
    var page;
    beforeAll(function () {
        page = new login_po_1.LoginPage();
        page.navigateTo();
    });
    it('should fail to log in when nothing is entered', function () {
        page.getLoginButton().click();
        expect(page.getFailedLoginResponse()).toEqual('Please enter a username and password\nUsername:\nPassword:\nSign in\nRegister');
    });
    it('should fail to login when incorrect credentials are entered', function () {
        page.getUsernameInput().sendKeys('1234');
        page.getPasswordInput().sendKeys('password');
        page.getLoginButton().click();
        expect(page.getFailedLoginResponse()).toEqual('Invalid username and/or password\nUsername:\nPassword:\nSign in\nRegister');
    });
});
describe('confirm login navigation', function () {
    var page;
    beforeAll(function () {
        page = new login_po_1.LoginPage();
        page.navigateTo();
    });
    it('should reach the index page', function () {
        page.getUsernameInput().sendKeys('TestAdmin');
        page.getPasswordInput().sendKeys('TestAdmin');
        page.getLoginButton().click();
        expect(page.getTitle()).not.toEqual('NGTrackForce');
    });
});
