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
	
	public ThreadUtil() {
		super();
	}
	
	public <T> T submitCallable(Callable<T> caller) {
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