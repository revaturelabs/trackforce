package com.revature.test;

import com.revature.utils.HibernateUtil;
import com.revature.utils.TestDBLoaderUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.sql.SQLException;

public class TestSuite extends BaseTest {
    @BeforeSuite
    public void setupHibernate() throws SQLException {
        // session factory already init in BaseTest CCtor
        new TestDBLoaderUtil().populate();
    }

    @AfterSuite
    public void shutdownHibernate() {
        HibernateUtil.shutdown();
    }
}
