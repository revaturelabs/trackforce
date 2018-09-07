package com.revature.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import static com.revature.utils.LogUtil.logger;

public class ThreadUtil {
	//I set it to default at 10 threads can be increased for more performance.
	//However, it will be to the detriment of the running server. (Most likely an Amazon EC2)
	private static ExecutorService executor = Executors.newFixedThreadPool(15);
	private static boolean lock = false; //used to prevent database calls while db reinitializes
	
	public ThreadUtil() {
		super();
	}
	
	public static void setLock(boolean lockStatus) {//setter for lock
		lock = lockStatus;
	}
	
	public <T> T submitCallable(Callable<T> caller) {
		
		while(lock) {//check if the lock is active
			try {
				Thread.sleep(1000);//causes the thread to sleep for 1 second
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Future<T> future = executor.submit(caller);
		T results = null;
		
		try {
			results = future.get();
		} catch (InterruptedException | ExecutionException e) {
			if(!future.isCancelled()) { future.cancel(true); }
			else {logger.info("Call was canceled");}
			logger.debug(e);
		}
		
		logger.debug("Current Active Threads: " + getActiveThreadCount());
		return results;
	}
	
	public static int getActiveThreadCount() {
		return ((ThreadPoolExecutor) executor).getActiveCount();
	}
	
	
}