package com.revature.test.dao;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.DBTestCase;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;

import java.io.File;
import java.io.FileInputStream;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.operation.DatabaseOperation;
import java.sql.DriverManager;
import java.sql.Connection;
/*DbUnit testing of dao/service code, inherits getConnection() from DBTestCase
 * Only one database used here, hence only one DbUnit
*/
public class DbUnit extends JdbcBasedDBTestCase{
   private Connection jdbcConnection;
    
    
   public DbUnit(String name){
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.OracleDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@trackforce.chgtukwhhsrg.us-east-1.rds.amazonaws.com:1521:ORCL" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "admin" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "trackforce" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "admin");
    }
 
    //NEED automation implementation to get XML from website
   //hardcoded location for xml file storing front-end data
    @Override
    protected IDataSet getDataSet() throws Exception {
       String filePath ="src/test/java/com/revature/test/dao/DbUnitDataset.xml";
       //  return new XmlDataSet(this.getClass().getClassLoader()
		//		.getResourceAsStream("DbUnitDataset.xml"));
    	IDataSet data = new XmlDataSet(new FileInputStream(new File(filePath)));
    	
        return data;
    }

    @Override
    protected String getConnectionUrl() {
        return System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL);
    }

    @Override
    protected String getDriverClass() {
        return System.getProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS);
    }
 
    @Override
    protected String getUsername() {
        return "admin" ;
    }

    @Override
    protected String getPassword() {
        return "trackforce";
    }

    @Override
    protected JdbcDatabaseTester getDatabaseTester() throws Exception {
    	 JdbcDatabaseTester tester = new JdbcDatabaseTester(this.getDriverClass(),this.getConnectionUrl(),this.getUsername(),this.getPassword(),this.getSchema());
         return tester;
    }
    
    protected String getSchema() {
		return System.getProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA);
    	
    }
    

    //modify default DbUnit clean up and set up operations to clean up database after test
    /*http://dbunit.sourceforge.net/components.html#cleanInsert
This operation literally refreshes dataset contents into the target database.
 This means that data of existing rows are updated and non-existing row get 
 inserted. Any rows which exist in the database but not in dataset stay 
 unaffected. This approach is more appropriate for tests that assume other
  data may exist in the database.if they are correctly written, 
  tests using this strategy can even be performed on a populated 
  database like a copy of a production database.*/   
    protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    //empty operation that does nothing
    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.NONE;
    }
 
}