package bcd.client.cli;

import java.io.File;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import bcd.crypto.Asymmetric;
import bcd.crypto.Crypto;
import bcd.crypto.KeyPairAccess;
import bcd.crypto.Symmetric;
import bcd.function.Block;
import bcd.function.Blockchain;
import bcd.function.Hasher;
import bcd.function.MerkleTree;
import bcd.function.Transaction;
import bcd.keygen.MyKeyPair;
import bcd.keygen.PredefinedCharsSecretKey;

public class App {

	public static void main(String[] args) throws Exception{
	
//		demo1();
//		tstHashing();
//		tstBlock();

//		tstBlockchain();
//		tstMerkleTree();
		
//		tstSymm();

		tstASymm();
	}
	
	/**
	 * tstASymm()
	 */
	static Asymmetric asymm = new Asymmetric("RSA");
	static void tstASymm() throws Exception
	{
		if( !new File("KeyPair").exists()  )
			MyKeyPair.create();
		else {
			//encrypt
//			PublicKey pubKey = KeyPairAccess.getPublicKey("KeyPair/PublicKey");
//			
//			String msg = "hello";
//			String encrypted = asymm.encrypt(msg, pubKey);
//			System.out.println( String.join("|", msg, encrypted) );
//			
			TimeUnit.SECONDS.sleep(3);
			//decrypt
			String encrypted = "dphZFqOdADzH9bKMZ1/AQwM2XAKwYlCvJdq3BvGCp7Os67vIZSEvBuXIdtHlsBrzUR5yZYKzQGVGdZm1kexrh1CS/rvIJe+fjuMaauicD52lxrEzE/tD9w/IzbFzJ2CFVAYv+Nkga5EnTYod0rcqz3xIHAv22r5K/sBVep4pLVc=";
			PrivateKey prvKey = KeyPairAccess.getPrivateKey("KeyPair/PrivateKey");
			String decrypted = asymm.decrypt(encrypted, prvKey);
			System.out.println("\n> Decrypted: "+ decrypted);
		
		}
	}
	
	/**
	 * tstSymm()
	 */
	static void tstSymm()
	{
		Crypto symm = new Symmetric("AES");
		
		Key myKey = PredefinedCharsSecretKey.create();
		
		//assume that a string data
		String data = "how do u do?";
		System.out.println( "Data: "+ data );
		
		try {
		
			String encrypted = symm.encrypt(data, myKey);
			System.out.println( "Encrypted: "+ encrypted );
			
			TimeUnit.SECONDS.sleep(3);

			String decrypted = symm.decrypt(encrypted, myKey);
			System.out.println("\nDecrypted: "+ decrypted);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("> Process complete!");
		}
		
	}

	/**
	 * tstMerkleTree()
	 */
	static void tstMerkleTree()
	{
		String[] arr = {
			"alice|bob|credit|rm10",
			"alice|bob|debit|rm20",
			"alice|bob|credit|rm30",
			"alice|bob|debit|rm4"
		};
		
		MerkleTree mt = MerkleTree.getInstance( Arrays.asList(arr) );
		mt.build();
		
		String root = mt.getRoot();
		
		System.out.println( "Root: " + root );
		//Root: e3bedf5688986ce3a90a21b8b7716777b66478a936a2fa2e96db0561399eedcc

		//any change!
		//Root: 6087128004e7a8606c1cbf491b26ae7db31012f6873a61e81d0e980c1b011686

		
	}
	
	/**
	 * tstBlockchain()
	 */
	static void tstBlockchain()
	{
		String tranx1 = "alice|bob|credit|1.0";
		String tranx2 = "alice|bob|debit|2.0";
		
		if ( !(new File(Blockchain.MASTER_BINARY).exists()) ) {
			//make master dir
			new File("master").mkdir();
			//create genesis block
			Blockchain.genesis();
		} else {
			Transaction tranxLst = new Transaction();
			tranxLst.add(tranx1);
			tranxLst.add(tranx2);
			
			String prevHash = Blockchain.get().getLast().getHeader().getCurrHash();
			Block b1 = new Block( prevHash );
			b1.setTranx(tranxLst);
			
			Blockchain.nextBlock(b1);
			Blockchain.distribute();
		}
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
