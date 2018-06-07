package com.revature.test.utils;

import org.apache.log4j.Logger;

 /* The Log class wraps log4j to log all desired test results. 
 * Log file location defined in log4j properties.
 * @author joeyi
 */
public final class Log {
	
	// .class gets an instance of the Log class at run time
	public final static Logger Log = Logger.getLogger(Log.class);
	
	//Instantiate log by logging start
	public Log()
	{
		Log.info("Start of new Test");
	}
	
	//Method to log failures only
	public static void failure(String msg) 
	{
		Log.fatal(msg);
	}
	
	public static void warn(String msg)
	{
		Log.warn(msg);
	}
	//Method to log debugging notes
	public static void debug(String msg)
	{
		Log.error(msg);
	}
	
	//for test success
	public static void success(String msg)
	{
		Log.info(msg);
	}

}