package com.revature.test.cuke;

import com.revature.utils.EnvManager;

/**
 * For constants used in the cucumber tests. This method is a static 
 * import to each of the cucumber test classes. 
 * @author GB
 *
 */
public class ConstantsCukeTestUtil {

	private static final String PASSWORD_CAPITALIZED = "Password";
	private static final String PASSWORD_LOWERCASE = "password";
	private static final String LOGIN = "login";
	private static final String LOGOUT = "logout";
	private static final String APP_HOME = "app-home";
	private static final String TRAINER_VIEW = "trainer-view";
	private static final String ASSOCIATE_VIEW = "associate-view";
	private static final String REVATURE_URL = "https://revature.com/";
	private static final String TEST_FIRST_NAME = "TestFirstName";
	private static final String TEST_LAST_NAME = "TestLastName";
	private static final String ASSOCIATE_VIEW_URL = "#/associate-view";
	private static final String baseUrl = EnvManager.NGTrackForce_URL;

	static final String getPasswordCapitalized() {
		return PASSWORD_CAPITALIZED;
	}
	static final String getPasswordLowercase() {
		return PASSWORD_LOWERCASE;
	}
	static final String getLogin() {
		return LOGIN;
	}
	static final String getLogout() {
		return LOGOUT;
	}
	static final String getAppHome() {
		return APP_HOME;
	}
	static final String getTrainerView() {
		return TRAINER_VIEW;
	}
	static final String getAssociateView() {
		return ASSOCIATE_VIEW;
	}
	static final String getRevatureUrl() {
		return REVATURE_URL;
	}
	static final String getTestFirstName() {
		return TEST_FIRST_NAME;
	}
	static final String getTestLastName() {
		return TEST_LAST_NAME;
	}
	static final String getAssociateViewUrl() {
		return ASSOCIATE_VIEW_URL;
	}
	static final String getBaseUrl() {
		return baseUrl;
	}
}
