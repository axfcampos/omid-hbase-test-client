



public class Main {




  private static void execute-transaction () {
      Configuration conf = HBaseConfiguration.create();
      TransactionManager tm = new TransactionManager(conf);
      TransactionalTable tt = new TransactionalTable(conf, TEST_TABLE);

      TransactionState t1 = tm.beginTransaction();

      Put put = new Put(row);
      putt.add(fam, col, data);
      tt.put(t1, p);

      ResultScanner rs = tt.getScanner(t1, new Scan().setStartRow(startrow).setStopRow(stoprow));
      Result r = rs.next();
      while (r != null) {
         ...
         r = rs.next();
      }
      tm.tryCommit(t1);
   }

  
  public static void main(String[] args) {


  }

}
