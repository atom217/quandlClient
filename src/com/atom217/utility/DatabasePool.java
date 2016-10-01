package com.atom217.utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabasePool {

	public static Session session = null;
	public static SessionFactory sf = null;
	public static SessionFactory getSf() {
		return sf;
	}


	public static void setSf(SessionFactory sf) {
		DatabasePool.sf = sf;
	}


	public DatabasePool() {
	}


	static{
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	
}
