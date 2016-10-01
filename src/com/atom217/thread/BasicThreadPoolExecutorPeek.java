package com.atom217.thread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.atom217.QueryCustom;
import com.atom217.dao.StockDaoImpl;
import com.atom217.utility.Stock;
 
public class BasicThreadPoolExecutorPeek
{
    public static void main(String[] args) 
    {
        //Use the executor created by the newCachedThreadPool() method 
        //only when you have a reasonable number of threads 
        //or when they have a short duration.
    	
    	//final BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    	//ThreadPoolExecutor executor = new ThreadPoolExecutor(101, 501,
    	 //       100000L, TimeUnit.SECONDS,
    	 //       queue);	
     ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);
        
  	  StockDaoImpl sd = new StockDaoImpl();
  	  List<String> allStock = sd.readStockInfoPeek();
  	  for(String stockName :allStock){
  		  Stock stock = new Stock(stockName);
  		  QueryTaskPeek task = new QueryTaskPeek(stock);
  		  System.out.println("A new task has been added : " + task.getStockName());
  		  executor.execute(task);
  	  }
  	  
  	  executor.shutdown();     

        
    }
}