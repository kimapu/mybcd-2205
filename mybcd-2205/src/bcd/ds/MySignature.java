package bcd.ds;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Base64;

public class MySignature {


	private Signature sig;

	/**
	 * Testing section (KeyPair)
	 */
	private KeyPairGenerator keygen;
	private KeyPair keyPair;
	{
		try {
			keygen = KeyPairGenerator.getInstance("RSA");
			keyPair = keygen.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//==========================================================================
	
	public MySignature() {
		super();
		try {
			sig = Signature.getInstance( "SHA256WithRSA" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * sign()
	 */
	public String sign(String data) throws Exception
	{
		sig.initSign( keyPair.getPrivate() );
		sig.update(data.getBytes());
		return Base64.getEncoder().encodeToString(sig.sign());
	}
	
	/**
	 * verify()
	 */
	public boolean verify(String data, String signature) throws Exception
	{
		sig.initVerify(keyPair.getPublic());
		sig.update(data.getBytes());
		return sig.verify( Base64.getDecoder().decode(signature));
	}

}
