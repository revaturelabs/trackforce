package com.revature.utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;

/*
 * The purpose of this class is to run a separate thread that is on
 * a timer and calls a stored procedure in the database. This procedure
 * will delete all associates if they are not approved for over 30 days time.
 * For speed/efficiency reasons, the logic is handled on PL/SQL side opposed
 * to iterating through collections on java side (causing thread issues).
 */
public class SchedulePendingUserCleanup extends TimerTask {

	private static SchedulePendingUserCleanup schedule;
	private Timer time = new Timer();

	public static void Start() {
		if (schedule == null) {
			schedule = new SchedulePendingUserCleanup();
		}
	}

	private SchedulePendingUserCleanup() {
		//will run every 24 hours
		time.schedule(this, 1000 * 60 * 60 * 24, 1000 * 60 * 60 * 24);
	}

	@Override
	public void run() {
		AssociateDaoImpl ad = new AssociateDaoImpl();
		//calls stored procedure
		ad.deleteOldAssociateProcedure();
	}
}
