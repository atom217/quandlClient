package com.atom217.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.atom217.dao.StockDaoImpl;
import com.atom217.utility.Stock;
import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.TabularResult;

public final class QueryTask implements Runnable{
	
	//private final static String QUANDL_TOKEN = "DipYgysX2_KDDjqx-BSU";	
	private final static String QUANDL_TOKEN ="fTtHv3dHbyWg1G1J9Mi4";
  private static final int ROW = 260;
  //private static final String SYMBOL = "NSE/JPPOWER";

  private Stock stock;
  
  public QueryTask(Stock stock) {
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
	    List<Double> highList = new ArrayList<Double>();
	    List<Double> lowList = new ArrayList<Double>();
	    for(int c=0;c<tabularResult.size();c++){
		   com.jimmoores.quandl.Row r = tabularResult.get(c);//2 - high 3 -low
		   
		   highList.add(r.getDouble(2));
		   lowList.add(r.getDouble(3));
		   //add to list 
	   }
	    
	   Collections.sort(highList ,Collections.reverseOrder());
	   Collections.sort(lowList);
	   
	   stock.setHighValue(highList.get(0));
	   stock.setLowValue(lowList.get(0));
	   
	   System.out.println("High >>"+highList.get(0).toString());
	   System.out.println("low >>"+lowList.get(0).toString()); 
	   System.out.println(tabularResult.toPrettyPrintedString()); 
	
	   //add this thing to database
	   StockDaoImpl addStock = new StockDaoImpl();
	   addStock.addHighLowValue(stock);
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
