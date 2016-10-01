package com.atom217;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.atom217.dao.StockDaoImpl;
import com.atom217.mapping.StockInfo;
import com.atom217.utility.Stock;
import com.jimmoores.quandl.DataSetRequest;
import com.jimmoores.quandl.Frequency;
import com.jimmoores.quandl.QuandlSession;
import com.jimmoores.quandl.Row;
import com.jimmoores.quandl.TabularResult;
import com.jimmoores.quandl.Transform;

/**
 * Example 2.
 */
public final class QueryCustom {
  private static final int CLOSE_COLUMN = 4;
  private static final int ROW = 1;
  private static final String SYMBOL = "NSE/JPPOWER";

  /**
   * Private default constructor.
   */
  private QueryCustom() {
  }
 
  /**
   * The main body of the code.
   */
  private Stock run(Stock stock) {
    
	  QuandlSession session = QuandlSession.create();
    
    // Webservice call
    TabularResult tabularResult = session.getDataSet(
            DataSetRequest.Builder
              .of(SYMBOL)
              .withMaxRows(ROW)
              .build()
             );
    
   //Business layer
    //List<Double> highList = new ArrayList<Double>();
    //List<Double> lowList = new ArrayList<Double>();
    Double nowValue = 0.0 ; 
    for(int c=0;c<tabularResult.size();c++){
	   Row r = tabularResult.get(c);//2 - high 3 -low
	   
	   nowValue = r.getDouble(4);
	  /* highList.add(r.getDouble(2));
	   lowList.add(r.getDouble(3));*/
	   //add to list 
   }
    
   stock.setNowValue(nowValue);
   System.out.println("low >>"+nowValue.toString());
    /*Collections.sort(highList ,Collections.reverseOrder());
   Collections.sort(lowList);
   stock.setHighValue(highList.get(0));
   stock.setLowValue(lowList.get(0));
   
   System.out.println("High >>"+highList.get(0).toString());
   System.out.println("low >>"+lowList.get(0).toString()); */
    
   System.out.println(tabularResult.toPrettyPrintedString());  
   
  return stock;
  }

  /**
   * Main entry point.
   * @param args command line arguments
   */
  public static void main(final String[] args) {
	  StockInfo em1 = new StockInfo();
	  StockDaoImpl sd = new StockDaoImpl();
	  //List<String> allStock = sd.readAllStockInfo();
	 // for(String stockName :allStock){
		  Stock stock = new Stock(SYMBOL);
		  QueryCustom example = new QueryCustom();
		  stock = example.run(stock);
		  StockDaoImpl addStock = new StockDaoImpl();
		   addStock.updateHighLowTable(stock);
	 // }
	  
	 
	  
	  System.exit(0);
  }
}
