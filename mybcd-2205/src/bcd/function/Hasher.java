package bcd.function;

import java.security.*;

import org.apache.commons.codec.binary.Hex;

public class Hasher {

	static public String md5(String input)
	{
		return hash(input, "MD5");
	}
	

	static public String sha256(String input)
	{
		return hash(input, "SHA-256");
	}
	
	/**
	 * @param blockBytes
	 * @return
	 */
	public static String sha256( byte[] blockBytes ) 
	{
		try {
			byte[] hashBytes = MessageDigest.getInstance("SHA-256").digest( blockBytes );
			return Hex.encodeHexString(hashBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	static public String sha384(String input)
	{
		return hash(input, "SHA-384");
	}

	static public String sha512(String input)
	{
		return hash(input, "SHA-512");
	}


	/**
	 * hash(String, String) : String
	 */
	private static String hash(String input, String algorithm) 
	{
		MessageDigest md;
		try 
		{
			//instantiate the MD object
			md = MessageDigest.getInstance(algorithm);
			//fetch input to MD
			md.update(input.getBytes());
			
			//add more secure value before hashing for the purpose to complicate the hashcode
			
			//1) add special chars
			//md.update( "secretchars".getBytes() );
			//2) add salting concept
//			md.update( Salt.generate() );
			
			//digest it
			byte[] hashBytes = md.digest();
			//convert to Hex format with Hex API from Apache common
			return String.valueOf(Hex.encodeHex(hashBytes));
		} 
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
