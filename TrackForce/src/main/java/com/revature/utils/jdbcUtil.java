package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class jdbcUtil {

	private static final String CONNECTION_USERNAME = "trackforcedev";
	private static final String CONNECTION_PASSWORD = "dW)gN$7%#xrQ2LV@";
	private static final String URL = "jdbc:oracle:thin:@trackforcedb.chueiwozbnfz.us-east-1.rds.amazonaws.com:1521:ORCL";

	private static Connection connection;
	
	/* Make tomcat know which database driver to use */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	// Singletons
	
	public static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			 
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}

}
