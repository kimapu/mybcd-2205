package bcd.function;

import java.util.Base64;

import java.util.Random;
import java.util.UUID;
import java.security.SecureRandom;


public class Salt {

	public static byte[] generate() {
		//creating rand value...
		SecureRandom sr = new SecureRandom();
		byte[] b = new byte[16];
		sr.nextBytes(b);
		return b;
	}
	
	public static void main(String[] args) {
		
		//how do you generate a rand value normally?
		System.out.println( Math.random() );
		System.out.println( new Random().nextInt() );
		System.out.println( UUID.randomUUID() );
		
		System.out.println( Base64.getEncoder().encodeToString(Salt.generate()) );
		
	}
	
}
