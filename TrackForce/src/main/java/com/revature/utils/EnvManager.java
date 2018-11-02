package com.revature.utils;

public class EnvManager {
	public final static String NGTrackForce_URL = System.getenv("NGTrackForce_URL");
	public final static String TomTrackForce_URL = System.getenv("TomTrackForce_URL");

	/**
	 * A shorthand for OsCheck.getOperatingSystemType()
	 * 
	 * @returns - the operating system detected
	 * 
	 * @author mussab
	 */
	public static OsCheck.OSType getOperatingSystemType() {
		return OsCheck.getOperatingSystemType();
	}

	/**
	 * helper class to check the operating system this Java VM runs in
	 *
	 * please keep the notes below as a pseudo-license
	 *
	 * http://stackoverflow.com/questions/228477/how-do-i-programmatically-determine-operating-system-in-java
	 * compare to
	 * http://svn.terracotta.org/svn/tc/dso/tags/2.6.4/code/base/common/src/com/tc/util/runtime/Os.java
	 * http://www.docjar.com/html/api/org/apache/commons/lang/SystemUtils.java.html
	 */

	public static final class OsCheck {
		/**
		 * types of Operating Systems
		 */
		public enum OSType {
			Windows, MacOS, Linux, Other
		};

		// cached result of OS detection
		protected static OSType detectedOS;

		/**
		 * detect the operating system from the os.name System property and cache the
		 * result
		 * 
		 * In case the os.name property has mac or darwin in it, it return MacOS In case
		 * it has win it return Windows In case it has nux it returns Linux Otherwise
		 * returns Other
		 * 
		 * @returns - the operating system detected
		 * 
		 * @author mussab
		 */
		public static OSType getOperatingSystemType() {
			if (detectedOS == null) {
				String OS = System.getProperty("os.name", "generic").toLowerCase(java.util.Locale.ENGLISH);
				if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
					detectedOS = OSType.MacOS;
				} else if (OS.indexOf("win") >= 0) {
					detectedOS = OSType.Windows;
				} else if (OS.indexOf("nux") >= 0) {
					detectedOS = OSType.Linux;
				} else {
					detectedOS = OSType.Other;
				}
			}
			return detectedOS;
		}
	}
}
