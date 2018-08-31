package com.revature.utils;


import java.util.stream.IntStream;

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
 * returns true or false so that the page can continue
 * 
 * effect: reduced the lines and complexity of the check for each individual authentication.
 */
	public static boolean Authorized (String token, int [] levelNeeded) {
		Claims payload = JWTService.processToken(token);
		// this checks to see if Id is in the array
		boolean result = IntStream.of(levelNeeded).anyMatch(x -> x == Integer.parseInt(payload.getId()));

		if (payload != null && result) {
			return false;
		}else {

			return true;
		}
	}


}


