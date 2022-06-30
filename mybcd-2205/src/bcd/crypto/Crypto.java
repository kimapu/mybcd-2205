package bcd.crypto;

import java.security.Key;

import javax.crypto.Cipher;

public abstract class Crypto {

	protected Cipher cipher;
	
	public Crypto(String param) {
		try {
			cipher = Cipher.getInstance( param );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract String encrypt(String data, Key key) throws Exception;
	public abstract String decrypt(String cipherText, Key key) throws Exception;

}
