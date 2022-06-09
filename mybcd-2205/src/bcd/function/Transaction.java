package bcd.function;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Serializable {

	public static final int SIZE = 10;
	/**
	 * we will comeback to generate the merkle root ie., hash of merkle tree
	 * 	merkleRoot = hash
	 */
	private String merkleRoot = "9a0885f8cd8d94a57cd76150a9c4fa8a4fed2d04c244f259041d8166cdfeca1b8c237b2c4bca57e87acb52c8fa0777da";
//	private String merkleRoot;	
	
	public String getMerkleRoot() {
		return merkleRoot;
	}

	public void setMerkleRoot(String merkleRoot) {
		this.merkleRoot = merkleRoot;
	}

	/**
	 * For the data collection, u may want to choose classic array or collection api
	 */
	private List<String> tranxLst;
	
	public List<String> getTranxLst() {
		return tranxLst;
	}

	public Transaction() {
		tranxLst = new ArrayList<>( SIZE );
	}

	/**
	 * add()
	 */
	public void add( String tranx ) {
		tranxLst.add(tranx);
	}
	
	@Override
	public String toString() {
		return "Transaction [tranxLst=" + tranxLst + "]";
	}
	
}
