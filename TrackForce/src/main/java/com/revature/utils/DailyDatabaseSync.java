package com.revature.utils;

import java.util.Timer;
import java.util.TimerTask;
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
		//will run every 24 hours
		time.schedule(this, 1000 * 60 * 60 * 24, 1000 * 60 * 60 * 24);
	}
	
	@Override
	public void run() {
		
		
	}

}
