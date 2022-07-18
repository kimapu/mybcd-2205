package bcd.crypto;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Asymmetric extends Crypto{

	public Asymmetric(String param) {
		super(param);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String encrypt(String data, Key key) throws Exception {
		String cipherText = null;
		//init
		super.cipher.init(Cipher.ENCRYPT_MODE, (PublicKey)key);
		//encrypt
		byte[] cipherBytes = cipher.doFinal( data.getBytes() );
		//convert to string
		cipherText = Base64.getEncoder().encodeToString(cipherBytes);
		return cipherText;
	}

	@Override
	public String decrypt(String cipherText, Key key) throws Exception {
		//init
		cipher.init(Cipher.DECRYPT_MODE, (PrivateKey)key);
		//convert to byte[]
		byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
		//decrypt
		byte[] dataBytes = cipher.doFinal( cipherBytes );  
		return new String( dataBytes );
	}

	
	
}
