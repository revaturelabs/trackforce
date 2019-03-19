package com.revature.utils;

import static com.revature.utils.LogUtil.logger;

import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mchange.v2.c3p0.C3P0Registry;
import com.mchange.v2.c3p0.PooledDataSource;

public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		HibernateUtil.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		@SuppressWarnings("unchecked")
		Set<PooledDataSource> pooledDataSourceSet = (Set<PooledDataSource>) C3P0Registry.getPooledDataSources();

		for (PooledDataSource dataSource : pooledDataSourceSet) {
			try {
				dataSource.hardReset();
				dataSource.close();
			} catch (SQLException e) {
				// note - do not use log4j since it may have been unloaded by this point
				logger.trace(e.getMessage(), e);
			}
		}
	}
}
