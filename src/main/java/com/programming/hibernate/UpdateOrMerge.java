package com.programming.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.programming.hibernate.entity.Student;
import com.programming.hibernate.util.HibernateUtil;

public class UpdateOrMerge {

	public static void main(String[] args) {
		Student student = new Student("Ramesh", "chatty", "ramesh@gmail.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            System.out.println("================ Save Start==================");
            session.save(student);
            System.out.println("================ Save complete==================");
            
            //session.merge(new Student("Ramesh", "chatty", "ramesh@gmail.com"));
            
            session.merge(student);
            
            transaction.commit();
        } catch (Exception e) {
        	
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
	}
}
