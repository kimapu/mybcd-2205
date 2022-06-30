package bcd.keygen;

import java.security.Key;
import java.util.Arrays;

import javax.crypto.spec.SecretKeySpec;

public class PredefinedCharsSecretKey {

	/**
	 * symmetric algorithm
	 */
	private static final String SYMM_ALGORITHM = "AES";
	
	/**
	 * predefined chars (secret)
	 */
	private static final String SECRET_CHARS = "this-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demothis-this-is-a-symmetric-key-demois-a-symmetric-key-demothis-is-a-symmetric-key-demothis-is-a-symmetric-key-demo";

	public static Key create()
	{
		int keysize = 16;
		return new SecretKeySpec( Arrays.copyOf(SECRET_CHARS.getBytes(), keysize), SYMM_ALGORITHM );
	}

}
