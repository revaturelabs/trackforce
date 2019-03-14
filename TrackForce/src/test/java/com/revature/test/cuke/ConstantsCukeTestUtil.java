package com.revature.test.cuke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	private static final String USERNAME_UPDATE = "username-update";
	private static final String PASSWORD_UPDATE = "password-update";
	private static final String APP_HOME = "app-home"; //uri for admin and staging views
	private static final String TRAINER_VIEW = "trainer-view";
	private static final String ASSOCIATE_VIEW = "associate-view";
	private static final String MY_INTERVIEW_VIEW = "myinterview-view"; 
	private static final String TAG_POST_LOGIN = "li";  //tag only exists post-login, used for waiting in PosiTest
	private static final String REVATURE_URL = "https://revature.com/";
	private static final String TEST_FIRST_NAME = "TestFirstName";
	private static final String TEST_LAST_NAME = "TestLastName";
	private static final String baseUrl = EnvManager.NGTrackForce_URL;
	private static final String MAIL_PREFIX = "mailto:";
	private static final String EMAIL_URL = "info@revature.com";
	private static final String INTERVIEW_DATE = setInterviewDate();
	private static final String INTERVIEW_TIME = setInterviewTime();
	private static final int INTERVIEW_TYPE = 2;
	private static final int INTERVIEWER = 77;

	static final String getInterviewTime() {
		return INTERVIEW_TIME;
	}
	static final String getMailPrefix(){
		return MAIL_PREFIX;
	}
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
	public static String getUsernameUpdate() {
		return USERNAME_UPDATE;
	}
	public static String getPasswordUpdate() {
		return PASSWORD_UPDATE;
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
	static final String getMyInterviewView() {
		return MY_INTERVIEW_VIEW;
	}
	public static String getTagPostLogin() {
		return TAG_POST_LOGIN;
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
	static final String getBaseUrl() {
		return baseUrl;
	}
	static final String getEmailUrl() {
		return EMAIL_URL;
	}
	public static String getInterviewDate() {
		return INTERVIEW_DATE;
	}
	public static int getInterviewType() {
		return INTERVIEW_TYPE;
	}
	public static int getInterviewer() {
		return INTERVIEWER;
	}
	
	private static String setInterviewTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String currentTime = dtf.format(now);
		currentTime = currentTime.replace(":", "");
		return currentTime;
	}
	
	private static String setInterviewDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mm/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String currentDate = dtf.format(now);
		currentDate = currentDate.replace("/", "");
		return currentDate;
	}
}
