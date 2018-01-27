package com.revature.runnable;

import java.io.IOException;

import com.revature.services.PersistentServiceDelegator;

/**
 * Used to handle caching on server startup.
 * Used to prevent server timeout on Virtual Machine (VM)
 * @author Antony Lulciuc
 */
public class PSDCacheRunner implements Runnable {
	public static final long DEFAULT_CACHE_START = 3000;   // TODO: CHANGE TO 30000
	private PersistentServiceDelegator psd = null;
	private long delayedStartTime = DEFAULT_CACHE_START;
	
	/**
	 * Constructor used to set caching mechanism and when to invoke/begin caching process
	 * @param psd - used to perform caching operation
	 * @param delayedStartTime - how long to wait before caching process begines
	 */
	public PSDCacheRunner(PersistentServiceDelegator psd, long delayedStartTime) {
		this.psd = psd;
		this.delayedStartTime = delayedStartTime;
	}
	
	/**
	 * Constructor used to set caching operation (sets delayedStartTime=DEFAULT_CACHE_START)
	 * @param psd- used to perform caching operation
	 */
	public PSDCacheRunner(PersistentServiceDelegator psd) {
		this.psd = psd;
	}
	
	/**
	 * Performs caching operations for persistent data used in application
	 */
	@Override
	public void run() {
		// Check if data used for caching is valid
		if (hasValidFields()) {
			delay();
			cache();
		}
	}
	
	///
	//	PRIVATE METHODS 
	///
	
	/**
	 * Determine if arguments supplied in constructor are valid
	 * @return true if delayedStartTime > -1 and psd not null else false
	 */
	private boolean hasValidFields() {
		return delayedStartTime > -1 && psd != null;
	}
	
	/**
	 * Delays caching process by specified interval [0, delayedStartTime]
	 */
	private void delay() {
		long current = System.currentTimeMillis();
		long start = delayedStartTime + current;
		
		// Set current time
		current = System.currentTimeMillis();

		// Loop until current time exceeds or equals start (begin caching)
		while (current < start) {
			current += System.currentTimeMillis() - current;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				// do nothing
			}
		}
	}
	
	/**
	 * Performs caching process
	 */
	private void cache() {

        try {
			// perform caching
            psd.getAssociates();
            psd.getBatches();
            psd.getClients();
            psd.getCurriculums();
            psd.getMarketingStatuses();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}	
}
