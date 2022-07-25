package bcd.exercise.dummytranx.solution;

import java.io.File;
import java.util.List;

import com.google.common.collect.Lists;

import bcd.function.Block;
import bcd.function.Transaction;

public class App {


	public static void main(String[] args) {
		
		String dir = "src/bcd/exercise/dummytranx";
		String myFile = "dummytranx.txt";
		
		TransactionDA daTranx = new TransactionDA(dir, myFile);
		//show
		//daTranx.getAll().forEach( System.out :: println );
		
		List<String> tranxLst = daTranx.getAll();
		boolean noChain = !((new File(MyBlockchain.MASTER_BINARY).exists()) & (new File(MyBlockchain.LEDGER_FILE).exists()));
		if( noChain )
		{
			new File(MyBlockchain.MASTER_DIR+"/master").mkdir();
			//create genesis block
			MyBlockchain.genesis();
		}
		else
		{
			List<List<String>> subLst = Lists.partition(tranxLst, Transaction.SIZE);
			for (List<String> lst : subLst) {
				Transaction bag = new Transaction();
				for (String line : lst) {
					bag.add(line);
				}
				//create nextBlock
				Block b1 = new Block( 
						MyBlockchain.get().getLast().getHeader().getCurrHash() 
						);
				b1.setTranx(bag);					
				MyBlockchain.nextBlock(b1);
				MyBlockchain.distribute();
			}
			
		}
		
	}
	
}
