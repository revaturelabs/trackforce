import { CreateUserPage } from "./create-user.po";
import { TestConfig } from "../configuration/test-config";

describe('create-user page input and button fields', () => {

    let createUser      : CreateUserPage;
    let testConfig      : TestConfig;
    let baseURL         : string;
    let testURL         : string;
    let username        : string;
    let password        : string;

    beforeAll(() => {
        createUser = new CreateUserPage();
        testConfig = new TestConfig();
        baseURL = testConfig.getBaseURL();
        testURL = '/createUser';
        username = 'user';
        password = 'password'
    });

    it('should navigate to the create-user page', () => {
        createUser.navigateTo();
        expect(createUser.getCurrentURL()).toEqual(baseURL + testURL);
    });

    it('should accept username input', () => {
        createUser.inputUsername(username);
        expect(createUser.getUsernameValue()).toEqual(username);
    });

    it('should accept password input', () => {
        createUser.inputPassword(password);
        expect(createUser.getPasswordValue()).toEqual(password);
    });

    it('should accept password confirmation input', () => {
        createUser.inputPasswordConfirm(password);
        expect(createUser.getPasswordConfirmValue()).toEqual(password);
    });

    it('should select admin radio button', () => {
        createUser.clickAdminRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('admin');
    });

    it('should select associate radio button', () => {
        createUser.clickAssociateRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('assoc');
    });

    it('should select director radio button', () => {
        createUser.clickDirectorRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('direct');
    });

    it('should select vp radio button', () => {
        createUser.clickVPRadio();
        expect(createUser.getCheckedRadioValue()).toEqual('vp');
    })
});