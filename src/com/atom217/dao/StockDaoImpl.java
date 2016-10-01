package com.atom217.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.atom217.mapping.StockHighLow;
import com.atom217.mapping.StockInfo;
import com.atom217.utility.DatabasePool;
import com.atom217.utility.Stock;

public class StockDaoImpl implements StockDao {

	public StockDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	
	@SuppressWarnings("deprecation")
	public static SessionFactory getSessionFactory() {
		   try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            return new Configuration().configure().buildSessionFactory();
	        } catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	}

	public List<String> readAllStockInfo() {
		Session session = getSessionFactory().openSession();
		//String hql = "SELECT symbol FROM StockInfo";
		String hql = "SELECT symbol FROM StockInfo where symbol NOT IN (SELECT stockId FROM StockHighLow)";
		Query query = session.createQuery(hql);
		 
		@SuppressWarnings("unchecked")
		List<String> stocks = query.list();
		
		session.close();
		System.out.println("Found " + stocks.size() + " Stocks");
		return stocks;

	}

	public List<String> readStockInfoPeek() {
		Session session = DatabasePool.getSf().openSession();
		//String hql = "SELECT symbol FROM StockInfo";
		String hql = "SELECT stockId FROM StockHighLow";
		Query query = session.createQuery(hql);
		 
		@SuppressWarnings("unchecked")
		List<String> stocks = query.list();
		
		session.close();
		System.out.println("Found " + stocks.size() + " Stocks");
		return stocks;

	}

	public void addHighLowValue(Stock stock) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);

		StockHighLow object = new StockHighLow(
								stock.getStockName(),
								stock.getLowValue(),
								stock.getHighValue(),
								reportDate);
		
		Session session = getSessionFactory().openSession();
		try{
		session.beginTransaction();
		session.save(object);
		session.getTransaction().commit();
		}catch(HibernateException e){
			
		}finally{
		session.close();
		System.out.println("Successfully created " + object.toString());
		}
				
	}


	public synchronized void updateHighLowTable(Stock stock) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);

		StockHighLow object = new StockHighLow();
		
//		session = getSessionFactory().openSession();
		Session session = DatabasePool.getSf().openSession();
		try{
		session.beginTransaction();
		object = (StockHighLow) session.load(StockHighLow.class, stock.getStockName());
		object = calculate(object,stock);
		object.setTimeStamp(reportDate);
		session.save(object);
		session.getTransaction().commit();
		}catch(HibernateException e){
			System.out.println("Hibernate exception raised : "+ e.getMessage());
			e.printStackTrace();
		}finally{
		session.close();
		System.out.println("#########################################################################################################################################");
		System.out.println("Successfully created " + object.toString());
		}
				

	}

	

	private StockHighLow calculate(StockHighLow object, Stock stock) {
		if (object.getHigh() < stock.getNowValue()){
			object.setHigh(stock.getNowValue());
		}
		if (object.getLow() > stock.getNowValue()){
			object.setLow(stock.getNowValue());
		}
		
		Double highP = ((object.getHigh() - stock.getNowValue())/object.getHigh())*100;
		Double lowP = ((stock.getNowValue()- object.getLow())/stock.getNowValue())*100;
		object.setHighP(highP);
		object.setLowP(lowP);
		object.setNowValue(stock.getNowValue());
		return object;
	}
	

}	