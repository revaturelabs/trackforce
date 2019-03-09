package com.revature.utils;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.revature.daoimpl.BatchDaoImpl;
import com.revature.entity.TfBatch;

import static com.revature.utils.LogUtil.logger;
/*
 * The purpose of this class is to receive Batch and Associate information daily from
 * the https://dev3.revature.com/docs API. Once the data is retrieved it adds Batch and 
 * Associate information to the TrackForce RDS.
 */
public class DailyDatabaseSync extends TimerTask {

	private static BatchDaoImpl batchdao = new BatchDaoImpl();
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
		logger.debug("Syncing batches with database");
		List<TfBatch> newBatches = Dev3ApiUtil.getBatchesEndingWithinLastNMonths(1);
		for (TfBatch b : newBatches) {
			logger.debug(b.getSalesforceId());
			TfBatch tempBatch = batchdao.getBatchBySalesforceId(b.getSalesforceId());
			if (tempBatch == null) {
				Dev3ApiUtil.loadBatchAndAssociatesIntoDB(b.getSalesforceId());
			}
		}
	}

}
