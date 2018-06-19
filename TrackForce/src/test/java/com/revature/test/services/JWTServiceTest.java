package com.revature.test.services;

import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeClass;

import com.revature.dao.UserDao;
import com.revature.entity.TfUser;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;

/**
* Not implemented at this time. Mocking tokens is tricky and may require a different approach
* to how we want to test tokens. Will return to this class as time allows but for now leaving
* untested.
* @author Jesse
* @since 6.18.06.08
*/
public class JWTServiceTest {

    @Mock
    private UserDao userDao;
    
    @InjectMocks
    private JWTService jwt = new JWTService();
    
    TfUser tf = new TfUser();
    Claims claim;
    
    private void setupMocks() {
        
        Mockito.when(userDao.getUser(Mockito.anyString())).thenReturn(tf);
        Mockito.when(userDao.getUser("baduser")).thenReturn(null);
        Mockito.when(JWTService.processToken("token")).thenReturn(claim);
        //Mockito.when(methodCall)
    }
    
    @BeforeClass
    public void beforeClass() {
        setupMocks();
    }
    
    @Test
    public void test() {
        String token = jwt.createToken("TestAdmin", 1);
        assertTrue(jwt.validateToken(token));
        String token2 = "asdfasddfae";
        assertFalse(jwt.validateToken(token2));
    }
}
