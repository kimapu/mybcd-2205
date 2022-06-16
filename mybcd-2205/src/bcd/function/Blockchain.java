package bcd.function;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.GsonBuilder;

/**
 * 
 * @author kimlee
 *
 *	persist and retrieve the blockchain to/from file (ledgerbook.txt)
 *
 */
public class Blockchain {

	/**
	 * constant variables
	 */
	
	//master-binary-file
	public static final String MASTER_BINARY = "master/chain.bin";
	
	//ledger-file
	private static final String LEDGER_FILE = "ledger.txt";

	//data-structure
	private static LinkedList<Block> db = new LinkedList<>();

	/**
	 * genesis()
	 * - very-first block
	 */
	public static void genesis()
	{
		Block genesis = new Block("0");
		db.add(genesis);
		Blockchain.persist();
		Blockchain.distribute();
	}
	
	/**
	 * nextBlock()
	 */
	public static void nextBlock( Block newBlock )
	{
		db = Blockchain.get();
		db.add(newBlock);
		Blockchain.persist();
	}
	
	
	/**
	 * get()
	 * - read the blockchain from the bin file
	 */
	public static LinkedList<Block> get()
	{
		try 
		(
			FileInputStream fin = new FileInputStream( MASTER_BINARY );
			ObjectInputStream in = new ObjectInputStream( fin );
		)
		{
			return (LinkedList<Block>)in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * persist()
	 * - write the blockchain to the bin file
	 */
	public static void persist()
	{
		try 
		(
			FileOutputStream fout = new FileOutputStream( MASTER_BINARY );
			ObjectOutputStream out = new ObjectOutputStream( fout );
		)
		{
			out.writeObject(db);
			System.out.println( ">> Master file updated!" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * distribute()
	 *  - show the ledger using gson prettyprint...
	 */
	public static void distribute()
	{
		/**
		 * convert the chain to String using API
		 */
		String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
		System.out.println( chain );
		try {
			Files.write(
					Paths.get(LEDGER_FILE), //targeted file
					chain.getBytes(), //content
					StandardOpenOption.CREATE //file mode
 					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
