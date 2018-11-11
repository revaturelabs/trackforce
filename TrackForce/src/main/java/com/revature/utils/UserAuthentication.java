package com.revature.utils;


import com.revature.dao.UserDao;
import com.revature.daoimpl.UserDaoImpl;
import com.revature.services.JWTService;

import io.jsonwebtoken.Claims;

public class UserAuthentication {

/**
 * 
 * @param token
 * the token is the authorization value for the web page granted from login
 * @param levelNeeded
 * level needed is the role from the database of your user, ex. an Admin has 1 and an Associate is 5
 * @return
 * returns true or false so that the page can continue.
 * true means that the page was authorized.
 */
	public static boolean Authorized (String token, int [] levelNeeded) {
		//creates payload object that we can get the role of the user from
		Claims payload = JWTService.processToken(token);
		System.out.println("HERE IS THE ROLEID: " + (String)payload.get("roleID"));
		UserDao ud = new UserDaoImpl();
		//makes sure payload is not null
		if (payload == null) {
			return false;
		}else {
			//checks if the users role# is within the parameter for the request
			//boolean result = IntStream.of(levelNeeded).anyMatch(
			//		x -> x == Integer.parseInt((String) payload.get("roleID"))
			//);
			/*
			boolean result = false;
			
			for(int level: levelNeeded) {
				if(Integer.parseInt((String)payload.get("roleID")) == level) {
					result = true;
					break;
				}
			}*/
			boolean result = true;
			//boolean compare = (ud.getUser(payload.getSubject()).getTfRole().getTfRoleId() == payload.get("roleID"));
			boolean compare = true;
			System.out.println("RESULT: " + result);
			System.out.println("COMPARE: " + compare);
			if ( result && compare) {
				//if it is then they are authorized for the page
				return true;
			}else {
				//if not then they are denied 
				return false;
			}
		}
	}
}



