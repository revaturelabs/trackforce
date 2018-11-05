package com.revature.utils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.revature.daoimpl.AssociateDaoImpl;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.entity.TfAssociate;
import com.revature.entity.TfUser;

public class SchedulePendingUserCleanup extends TimerTask {

	private static SchedulePendingUserCleanup schedule;
	private Timer time = new Timer();

	public static void Start() {
		System.out.println("started");
		if (schedule == null) {
			schedule = new SchedulePendingUserCleanup();
		}
	}

	private SchedulePendingUserCleanup() {
		time.schedule(this, 20000, 1000 * 60 * 1);
	}

	@Override
	public void run() {
		Thread.currentThread().setPriority(10);
		System.out.println("im hereeeeee");
		// initialize dao's needed
		UserDaoImpl ud = new UserDaoImpl();
		AssociateDaoImpl ad = new AssociateDaoImpl();

		// set temporary user for deletion after checks
		TfUser tempUser = new TfUser();
		// set todays date - 30days for comparing user start date
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		// represents amount of milliseconds in 1 month
		Timestamp cutOffDate = new Timestamp(ts.getTime() - (2592000L * 1000L));
		// will be used for associate start date
		Timestamp submissionDate;
		// list of all associates
		List<TfAssociate> associates = ad.getAllAssociates();
		/*
		 * iterates through associate list and checks if start date is greater than 30
		 * days, and checks if they are not approved. If they are not approved and
		 * greater than 30 days the application will remove the user from the database.
		 */
		System.out.println(associates.get(4).toString());
		System.out.println("about to finaaaaa");
		
		for (TfAssociate a : associates) {
			System.out.println("im looping");
			// set submission date for current element
			submissionDate = a.getClientStartDate();
			int currentId = a.getUser().getId();
			// set tempUser to current id of associate
			tempUser = ud.getUser(currentId);
			// complete date checks prior to deletion & is not approved
			System.out.println("about to do checking");
			if (submissionDate.before(cutOffDate) && tempUser.getIsApproved() == 0) {
				
				System.out.println(a.toString());
				System.out.println("about to delete");
				// delete user from associate table first
				try {
				
					System.out.println(tempUser.getId());
					System.out.println(a.getId());
					ad.deleteAssociate(a);
					ud.deleteUser(tempUser);
				}catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("did i work?");
				// delete user from user table
				// ud.deleteUser(tempUser);
			}
		}
		System.out.println("looped out");
	}

	public static void main(String[] args) {
		SchedulePendingUserCleanup.Start();
	}
}
