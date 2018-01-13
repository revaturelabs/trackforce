package com.revature.dao;

import static org.testng.Assert.*;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.revature.entity.TfRole;
import com.revature.entity.TfUser;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordStorage;
import com.revature.utils.PasswordStorage.CannotPerformOperationException;

public class UserDaoImplTest {

	UserDaoImpl uDao = new UserDaoImpl();

	@DataProvider(name = "userName")
	public String[] userName() {
		return new String[] { "TestAdmin" };
	}
	
	
	@DataProvider(name = "user")
	  public Object[][] user() {
			return new Object[][] {
				new Object[] {new TfUser(new BigDecimal(1))}
			};
	}


	@Test(dataProvider = "userName")
	public void getUserString(String username) throws IOException {
		TfUser result = uDao.getUser(username);
		assertNotNull(result);
		System.out.println("username: " + result.getTfUserUsername());
	}

}
