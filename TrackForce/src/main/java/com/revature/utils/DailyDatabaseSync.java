package com.revature.utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
/*
 * The purpose of this class is to receive Batch and Associate information daily from
 * the https://dev3.revature.com/docs API. Once the data is retrieved it adds Batch and 
 * Associate information to the TrackForce RDS.
 */
public class DailyDatabaseSync extends TimerTask {

	private static DailyDatabaseSync schedule;
	private Timer time = new Timer();

	public static void Start() {
		if (schedule == null) {
			schedule = new DailyDatabaseSync();
		}
	}

	private DailyDatabaseSync() {
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 2);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		
		time.schedule(this, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	}
	
	@Override
	public void run() {
		System.out.println("Daily Database Sync");
		
	}

}
