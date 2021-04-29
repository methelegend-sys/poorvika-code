package com.nagarro.ImageManagement.Utilities;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.nagarro.ImageManagement.entities.Images;
import com.nagarro.ImageManagement.entities.User;

public class HibernateUtil {
	
	private static SessionFactory factory; 
	private static Configuration configuration = new Configuration();
	
	public static SessionFactory getFactory() {
		configuration.configure();
	    configuration.addAnnotatedClass(User.class);
	    configuration.addAnnotatedClass(Images.class);
	    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    factory = configuration.buildSessionFactory(serviceRegistry);
	   
	    return factory;
	}
	

}

