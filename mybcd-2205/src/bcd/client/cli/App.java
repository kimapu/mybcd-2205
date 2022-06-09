package bcd.client.cli;

import java.util.Arrays;
import java.util.List;

import bcd.function.Block;
import bcd.function.Hasher;
import bcd.function.Transaction;

public class App {

	public static void main(String[] args) {
	
//		demo1();
//		tstHashing();
		tstBlock();
	}

	/**
	 * tstBlock()
	 */
	static void tstBlock()
	{
		//very-first block called genesis block
		Block genesis = new Block("0");
		System.out.println( genesis );
		
		String tranx1 = "alice|bob|rm10";
		String tranx2 = "helen|bob|rm20";
		
		Transaction tranxLst = new Transaction();
		tranxLst.add(tranx1);
		tranxLst.add(tranx2);
	
		//block-1
		Block b1 = new Block(genesis.getHeader().getCurrHash());
		b1.setTranx(tranxLst);
		System.out.println( b1 );
		
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
