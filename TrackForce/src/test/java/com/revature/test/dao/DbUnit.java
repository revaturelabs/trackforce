package com.revature.test.dao;
import org.dbunit.JdbcBasedDBTestCase;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import java.io.FileInputStream;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
/*DbUnit testing of dao code, inherits getConnection() from DBTestCase
*/
public class DbUnit extends JdbcBasedDBTestCase{

   public DbUnit(String name){
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@:trackforce.chgtukwhhsrg.us-east-1.rds.amazonaws.com:1521:ORCL" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "admin" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "trackforce" );
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("dataset.xml"));
        
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
        return super.getUsername();
    }

    @Override
    protected String getPassword() {
        return super.getPassword();
    }
    @Override
    protected IDatabaseTester getDatabaseTester() throws Exception {
        return super.getDatabaseTester();
    }
 
}