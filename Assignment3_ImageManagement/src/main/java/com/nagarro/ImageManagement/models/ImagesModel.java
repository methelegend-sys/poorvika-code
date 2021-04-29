package com.nagarro.ImageManagement.models;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.nagarro.ImageManagement.Utilities.HibernateUtil;
import com.nagarro.ImageManagement.entities.Images;

public class ImagesModel {
	
private static SessionFactory factory;
	
	public ImagesModel() {
		factory  =  HibernateUtil.getFactory();
	}
	
	public boolean addImages(String imagePath, double size, int userId,String name) {
		  Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	         Images image  = new Images(imagePath, size, userId, name);
	         int id  = (Integer) session.save(image);
	        
	         tx.commit();
	         if(id>0) {
	        	 return true;
	         }
	         else {
	        	 return false;
	         }
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         return false;
	      } finally {
	         session.close(); 
	      }
	}
	
	public boolean updateImages(Images image) {
		  Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	        session.update(image);
	        
	         tx.commit();
	       
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         return false;
	      } finally {
	         session.close(); 
	      }
	      
	      return true;
	}
	
	public boolean deleteImage(Images image) {
		  Session session = factory.openSession();
	      Transaction tx = null;
	      
	      try {
	         tx = session.beginTransaction();
	     session.remove(image);
	        
	         tx.commit();
	       
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	         return false;
	      } finally {
	         session.close(); 
	      }
	      
	      return true;
	}
	
	@SuppressWarnings("unchecked")
	public double getTotalSize(Integer userId){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      List<Images> images = null;
	      double size =0;
	      try {
	         tx = session.beginTransaction();
	         images = session.createQuery("FROM Images WHERE userId = "+userId).list(); 
	         tx.commit();
	         if(!images.isEmpty()) {
	        	 Iterator<Images> it = images.iterator();
	        	 while(it.hasNext()) {
	        		 size = size + it.next().getSize();
	        	 }
	         }
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return size;
	   }
	
	public Images getImage(Integer imageId){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      Images images = null;
	      try {
	         tx = session.beginTransaction();
	         images = (Images) session.createQuery("FROM Images WHERE id = "+imageId).uniqueResult(); 
	         tx.commit();
	       
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return images;
	   }
	
	@SuppressWarnings("unchecked")
	public List<Images> listImages(Integer userId){
	      Session session = factory.openSession();
	      Transaction tx = null;
	      List<Images> images = null;
	      
	      try {
	         tx = session.beginTransaction();
	         images = session.createQuery("FROM Images WHERE userId = "+userId).list(); 
	         tx.commit();
	        
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return images;
	   }
	
	public String getReadableSize(double bytes) {
	    String s = bytes < 0 ? "-" : "";
	    long b = (long) (bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes));
	    return b < 1000L ? bytes + " B"
	            : b < 999_950L ? String.format("%s%.1f kB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f MB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f GB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f TB", s, b / 1e3)
	            : (b /= 1000) < 999_950L ? String.format("%s%.1f PB", s, b / 1e3)
	            : String.format("%s%.1f EB", s, b / 1e6);
	}
	
	

}
