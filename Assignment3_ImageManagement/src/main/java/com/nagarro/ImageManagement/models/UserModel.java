package com.nagarro.ImageManagement.models;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.ImageManagement.Utilities.HibernateUtil;
import com.nagarro.ImageManagement.entities.User;

public class UserModel {
	
	private static SessionFactory factory;
	
	public UserModel() {
		factory  =  HibernateUtil.getFactory();
	}
	
	public boolean addUser(String userName, String firstName, String lastName, String password) {
		  Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         @SuppressWarnings("unused")
			User user = new User(userName,firstName,lastName,password);
	         tx.commit();
	         return true;
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         return false;
	      } finally {
	         session.close(); 
	      }
	}
	@SuppressWarnings("unchecked")
	public List<User> listUsers(){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      List<User> users = null;
	      
	      try {
	         tx = session.beginTransaction();
	         users = session.createQuery("FROM User").list(); 
	         tx.commit();
	        
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return users;
	   }
	
	
	public User getUser(String username){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      User user = null;
	      
	      try {
	         tx = session.beginTransaction();
	         @SuppressWarnings("unchecked")
			Query<User> query = session.createQuery("FROM User WHERE username LIKE '"+username+"'");
	        
	         user = (User) query.uniqueResult(); 
	         tx.commit();
	        
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return user;
	   }
	
	
	

}
