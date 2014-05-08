package pt.inescid.gsd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import com.yahoo.omid.transaction.*;




public class Main {

	protected final static String TEST_TABLE = "test";
	protected final static String ROW = "row1";
	protected final static String FAM = "cf1";
	protected final static String COL = "cl1";
	protected final static String data = "The restaurant at the end of the universe";

  private static void executeTransaction () throws Exception {
		Configuration conf = HBaseConfiguration.create();
		TransactionManager tm = new TransactionManager(conf);
		TTable tt = new TTable(conf, TEST_TABLE);

    Transaction t1 = tm.begin();
     
    Put put = new Put(ROW.getBytes());
    put.add(FAM.getBytes(), COL.getBytes(), data.getBytes());
    
    tt.put(t1, put);
    
    ResultScanner rs = 
    		tt.getScanner(
    				t1, 
    				new Scan().setStartRow(ROW.getBytes()).setStopRow(ROW.getBytes()));
    Result r = rs.next();
    while (r != null) {
    	
    	r = rs.next();
    }
    
    tm.commit(t1);
    
   }

  
  public static void main(String[] args) {

  	try{
  		executeTransaction();
  	} catch(Exception e){
  		e.printStackTrace();
  	}
  }

}
