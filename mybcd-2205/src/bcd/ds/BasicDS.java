package bcd.ds;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;

public class BasicDS {


	/**
	 * hashing and asymm used
	 */
	
	/**
	 * Algorithm
	 */
	private final String HASHING_ALGO = "SHA-256";
	private final String CRYPTO_ALGO = "RSA";
	
	/**
	 * Testing section (KeyPair)
	 */
	private Cipher cipher;
	public BasicDS() {
		super();
		try {
			cipher = Cipher.getInstance(CRYPTO_ALGO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private KeyPairGenerator keygen;
	private KeyPair keyPair;
	{
		try {
			keygen = KeyPairGenerator.getInstance(CRYPTO_ALGO);
			keyPair = keygen.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//==========================================================================
	
	/**
	 * hash function
	 */
	public byte[] hash( String data ) {
		try {
			return MessageDigest.getInstance( HASHING_ALGO ).digest( data.getBytes() );
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * éncrypt function
	 */
	public String encrypt( byte[] hash ) {
		String signature = "";
		try {
			cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
			signature = Base64.getEncoder().encodeToString( cipher.doFinal(hash) );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return signature;
	}
	
	/**
	 * verify function
	 */
	public boolean verify( String data, String signature ) {
		byte[] dataHash = this.hash(data);
		try {
			cipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
			byte[] signatureBytes = cipher.doFinal( Base64.getDecoder().decode(signature) );
			return Arrays.equals(dataHash, signatureBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
