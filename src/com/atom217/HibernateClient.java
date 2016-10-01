package com.atom217;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.atom217.mapping.StockInfo;

public class HibernateClient {
	
	public static void main(String[] args) {
		
		//StockInfo em1 = new StockInfo();
		/*Employee em2 = new Employee("John Aces", 32);
		Employee em3 = new Employee("Ian Young", 29);*/
		
	/*	System.out.println(" =======CREATE =======");
		create(em1);
		create(em2);
		create(em3);*/
		String em1 = new String();
		System.out.println(" =======READ =======");
		List<String> ems1 = read();
		for(String e: ems1) {
			System.out.println(e);
		}
/*		System.out.println(" =======UPDATE =======");
		em1.setAge(44);
		em1.setName("Mary Rose");
		update(em1);
		System.out.println(" =======READ =======");
		List<Employee> ems2 = read();
		for(Employee e: ems2) {
			System.out.println(e.toString());
		}
		System.out.println(" =======DELETE ======= ");
		delete(em2.getId());
		System.out.println(" =======READ =======");
		List<Employee> ems3 = read();
		for(Employee e: ems3) {
			System.out.println(e.toString());
		}
		System.out.println(" =======DELETE ALL ======= ");
		deleteAll();
		System.out.println(" =======READ =======");
		List<Employee> ems4 = read();
		for(Employee e: ems4) {
			System.out.println(e.toString());
		}*/
		System.exit(0);
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


	public static List<String> read() {
		Session session = getSessionFactory().openSession();
		String hql = "SELECT symbol FROM StockInfo";
		Query query = session.createQuery(hql);
		 
		@SuppressWarnings("unchecked")
		List<String> stocks = query.list();
		session.close();
		System.out.println("Found " + stocks.size() + " Stocks");
		return stocks;

	}
/*	public static String create(StockInfo e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully created " + e.toString());
		return e.getSymbol();
		
	}
*/
	/*public static void update(Employee e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Employee em = (Employee) session.load(Employee.class, e.getId());
		em.setName(e.getName());
		em.setAge(e.getAge());
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully updated " + e.toString());

	}

	public static void delete(Integer id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Employee e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted " + e.toString());

	}

	public static Employee findByID(Integer id) {
		Session session = getSessionFactory().openSession();
		Employee e = (Employee) session.load(Employee.class, id);
		session.close();
		return e;
	}
	
	public static void deleteAll() {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM Employee ");
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		System.out.println("Successfully deleted all employees.");

	}
*/	
}

