package com.revature.runnable;

import static com.revature.config.DataSourceBuilder.Constants.PASS_KEY;
import static com.revature.config.DataSourceBuilder.Constants.URL_KEY;
import static com.revature.config.DataSourceBuilder.Constants.USERNAME_KEY;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.revature.config.TomcatJDBCDataSourceBuilder;
import com.revature.services.PersistentServiceDelegator;
import com.revature.utils.HibernateUtil;

/**
 * Used to handle caching on server startup.
 * Used to prevent server timeout on Virtual Machine (VM)
 * @author Antony Lulciuc
 */
public class PSDCacheRunner implements Runnable {
	public static final long DEFAULT_CACHE_START = 30000;
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
		try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("tomcat-jdbc.properties")) {
			Properties props = new Properties();
			String urlEnvironmentVariable;
			String usernameEnvironmentVariable;
			String passwordEnvironmentVariable;
			
			// Load propoerty data
			props.load(is);
			
	        // read in the environment variables
	        urlEnvironmentVariable = props.getProperty(URL_KEY);
	        usernameEnvironmentVariable = props.getProperty(USERNAME_KEY);
	        passwordEnvironmentVariable = props.getProperty(PASS_KEY);

	        // replace environment variable names with their actual values
	        props.setProperty(URL_KEY, System.getenv(urlEnvironmentVariable));
	        props.setProperty(USERNAME_KEY, System.getenv(usernameEnvironmentVariable));
	        props.setProperty(PASS_KEY, System.getenv(passwordEnvironmentVariable));
	        
	        // init data source builded 
			HibernateUtil.setDataSourceBuilder(new TomcatJDBCDataSourceBuilder(), props); 

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
