package bcd.client.cli;

import java.util.Arrays;
import java.util.List;

import bcd.function.Hasher;

public class App {

	public static void main(String[] args) {
	
//		demo1();
		tstHashing();
	}

	/**
	 * tstHashing
	 */
	static void tstHashing()
	{
		String pwd1 = "iwanttohackyou";
		String hash1 = Hasher.md5(pwd1);
		System.out.println( String.join("|", "MD5", hash1) );
		System.out.println( String.join("|", "SHA-256", Hasher.sha256(pwd1)) );
		System.out.println( String.join("|", "SHA-384", Hasher.sha384(pwd1)) );
		System.out.println( String.join("|", "SHA-512", Hasher.sha512(pwd1)) );
		
	}
	
	
	/**
	 * Object.hashcode()
	 */
	static void demo1()
	{
		String s1 = "alibaba";
		String s2 = "mick";
		System.out.println( String.join(":", s1, ""+s1.hashCode()) );
		System.out.println( String.join(":", s2, ""+s2.hashCode()) );

		System.out.println();
		//Collection
		List<String> nameLst = Arrays.asList(s1, s2, "john");
		System.out.println( String.join(":", nameLst.toString(), String.valueOf(nameLst.hashCode())) ); //[alibaba, mick]:1717932777

	}
	
}
