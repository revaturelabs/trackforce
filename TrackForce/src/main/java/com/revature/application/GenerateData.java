package com.revature.application;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/** @author Adam L. 
 * <p>Class for generating random primitives</p>
 * <p>Could be used for quickly populating database with dummy data</p>
 * @version v6.18.06.13 */
public class GenerateData {
	public static String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) { 
            int index = (rnd.nextInt() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }
	
	public static int getRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}