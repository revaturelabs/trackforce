package com.revature.application;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


/**
 * @author Adam L. 
 * <p>Class for generating random primitives</p>
 * <p>Could be used for quickly populating database with dummy data</p>
 * @version.date v6.18.06.13
 */
public class GenerateData {
	public static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	public static int getRandomInt(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
    }
}
