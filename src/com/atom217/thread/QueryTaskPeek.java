package com.atom217.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.atom217.dao.StockDaoImpl;
import com.atom217.mapping.StockHighLow;
import com.atom217.mapping.StockInfo;
import com.atom217.utility.Stock;
import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.Frequency;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;
import com.jimmoores.quandl.Transform;

public final class QueryTaskPeek implements Runnable{
	
	//private final static String QUANDL_TOKEN = "DipYgysX2_KDDjqx-BSU";
	private final static String QUANDL_TOKEN ="fTtHv3dHbyWg1G1J9Mi4"; //abhi
  private static final int ROW = 1; //only latest value
  //private static final String SYMBOL = "NSE/JPPOWER";

  private Stock stock;
  
  public QueryTaskPeek(Stock stock) {
	  this.stock = stock;
  }
  
  public Stock getStock(){
	  return stock;
  }
  
  public String getStockName(){
	  return stock.getStockName();
  }
 
  /**
   * The main body of the code.
   */
  public void run() {
    
		 QuandlSession session = QuandlSession.create(QUANDL_TOKEN);
		 System.out.println("Doing a task during : " + stock.getStockName());
	    // Webservice call
	    TabularResult tabularResult = session.getDataSet(
	            DataSetRequest.Builder
	              .of(stock.getStockName())
	              .withMaxRows(ROW)
	              .build()
	             );
	    
	   //Business layer
	    Double latestValue = 0.0; 
	    for(int c=0;c<tabularResult.size();c++){
		   Row r = tabularResult.get(c);//2 - high 3 -low 4-last
		   latestValue = r.getDouble(4);
		   /*highList.add(r.getDouble(2));
		   lowList.add(r.getDouble(3));*/
		   //add to list 
	   }
	    
	   
	   stock.setNowValue(latestValue);
	   System.out.println("latest >>"+latestValue.toString());
	   /*System.out.println("low >>"+lowList.get(0).toString()); */
	  // System.out.println(tabularResult.toPrettyPrintedString()); 
	
	   //add this thing to database
	   StockDaoImpl addStock = new StockDaoImpl();
	   addStock.updateHighLowTable(stock);
  }
  

  /**
   * Main entry point.
   * @param args command line arguments
   */
 /* public static void main(final String[] args) {
	  StockInfo em1 = new StockInfo();
	  StockDaoImpl sd = new StockDaoImpl();
	  List<String> allStock = sd.readAllStockInfo();
	  for(String stockName :allStock){
		  Stock stock = new Stock(stockName);
		  QueryCustom example = new QueryCustom();
		  stock = example.run(stock);
	  }
	  
	 
	  
	  System.exit(0);
  }*/
}
