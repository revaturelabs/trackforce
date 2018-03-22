package com.revature.utils;

import org.apache.log4j.Logger;

/**
 * Final utility class containing root logger declaration used across the
 * project
 */


public final class LogUtil {

	private LogUtil() {
	}

	public static final Logger logger = Logger.getLogger(LogUtil.class);
}
