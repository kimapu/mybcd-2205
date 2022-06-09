package bcd.function;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable{

	private Header header;

	public Header getHeader() {
		return header;
	}

	private Transaction tranx;

	public Block( String previousHash ) {
		header = new Header();
		header.setTimestamp( new Timestamp(System.currentTimeMillis()).getTime() );
		header.setPrevHash(previousHash);
		String blockHash = Hasher.sha256(getBytes());
		header.setCurrHash( blockHash );
	}
	
	/**
	 * getBytes of the Block object
	 */
	private byte[] getBytes() {
		try 
		(
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream( baos );
		)
		{
			out.writeObject(this);
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Transaction getTranx() {
		return tranx;
	}

	/**
	 * aggregation rel
	 */
	public void setTranx(Transaction tranx) {
		this.tranx = tranx;
	}

	/**
	 * composition rel
	 */
	public class Header implements Serializable {
		private int index;
		private String currHash, prevHash;
		private long timestamp;

		// getset methods
		public String getCurrHash() {
			return currHash;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public void setCurrHash(String currHash) {
			this.currHash = currHash;
		}

		public String getPrevHash() {
			return prevHash;
		}

		public void setPrevHash(String prevHash) {
			this.prevHash = prevHash;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		@Override
		public String toString() {
			return "Header [index=" + index + ", currHash=" + currHash + ", prevHash=" + prevHash + ", timestamp="
					+ timestamp + "]";
		}

	}

	@Override
	public String toString() {
		return "Block [header=" + header + ", tranx=" + tranx + "]";
	}

}
