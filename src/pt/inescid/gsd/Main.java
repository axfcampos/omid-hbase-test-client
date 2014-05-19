package pt.inescid.gsd;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;

import com.yahoo.omid.transaction.*;

public class Main {

	protected final static String TEST_TABLE = "test";
	protected final static String ROW = "row4";
	protected final static String FAM = "cf1";
	protected final static String COL = "cl1";
	protected final static String data = "The third restaurant at the end of the universe";

  private static void executeTransaction () throws Exception {
  	
  	//HBase configuration
		Configuration conf = HBaseConfiguration.create();
		conf.set("tso.host", "ginja-a1");
		conf.setInt("tso.port", 1234);
		
		
		
		//Transaction start
		TransactionManager tm = new TransactionManager(conf);
		TTable tt = new TTable(conf, TEST_TABLE);

    Transaction t1 = tm.begin();
     
    Put put = new Put(ROW.getBytes());
    put.add(FAM.getBytes(), COL.getBytes(), data.getBytes());
    tt.put(t1, put);
    
    tm.commit(t1);
    
    tt.close();
    
    //Transaction end
    
   }

  
  public static void main(String[] args) {

  	try{
  		System.out.println("...Starting");
  		executeTransaction();
  		System.out.println("...leaving");
  	} catch(Exception e){
  		System.out.println(e);
  		e.printStackTrace();
  	}
  }

}
