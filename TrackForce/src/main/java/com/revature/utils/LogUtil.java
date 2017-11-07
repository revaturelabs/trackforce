package com.revature.utils;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 *  Final utility class containing root logger declaration used across the project   
 */
public final class LogUtil {

    public static final Logger logger = LogManager.getRootLogger();
}