package bcd.keygen;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.KeyGenerator;

public class RandomSecretKey {

	/**
	 * symmetric algorithm
	 */
	private static final String SYMM_ALGORITHM = "AES";

	public static Key create() {
		try {
			KeyGenerator kg = KeyGenerator.getInstance(SYMM_ALGORITHM);
			kg.init(256, new SecureRandom());
			return kg.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		
		Optional<Key> mykey = Optional.ofNullable(RandomSecretKey.create());
		mykey.ifPresent( elem -> System.out.println( Base64.getEncoder().encodeToString( elem.getEncoded() ) ) );
		
	}
	

}
