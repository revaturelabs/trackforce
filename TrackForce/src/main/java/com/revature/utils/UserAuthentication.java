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
 * returns true or false so that the page can continue.
 * true means that the page was authorized.
 */
	public static boolean Authorized (String token, int [] levelNeeded) {
		//creates payload object that we can get the role of the user from
		Claims payload = JWTService.processToken(token);
		//makes sure payload is not null
		if (payload == null) {
			return false;
		}else {
			//checks if the users role# is within the parameter for the request
			boolean result = IntStream.of(levelNeeded).anyMatch(
					x -> x == Integer.parseInt(payload.getId())
			);
			
			if ( result) {
				//if it is then they are authorized for the page
				return true;
			}else {
				//if not then they are denied 
				return false;
			}
		}
	}
}


