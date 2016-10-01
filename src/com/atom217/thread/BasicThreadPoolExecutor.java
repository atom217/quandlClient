package com.atom217.thread;

import java.util.List;
import java.util.concurrent.Executors;

import java.util.concurrent.ThreadPoolExecutor;
import com.atom217.dao.StockDaoImpl;
import com.atom217.utility.Stock;
 
public class BasicThreadPoolExecutor
{
    public static void main(String[] args) 
    {
        //Use the executor created by the newCachedThreadPool() method 
        //only when you have a reasonable number of threads 
        //or when they have a short duration.
    	
    	/*final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(50);
    	ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20,
    	        100000L, TimeUnit.SECONDS,
    	        queue);	*/
      ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(60);
        
  	  StockDaoImpl sd = new StockDaoImpl();
  	  List<String> allStock = sd.readAllStockInfo();
  	  for(String stockName :allStock){
  		  Stock stock = new Stock(stockName);
  		  QueryTask task = new QueryTask(stock);
  		  System.out.println("A new task has been added : " + task.getStockName());
  		  executor.execute(task);
  	  }
  	  
  	  executor.shutdown();     

        
    }
}