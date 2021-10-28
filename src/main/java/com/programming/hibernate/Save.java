package com.programming.hibernate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.programming.hibernate.entity.Student;
import com.programming.hibernate.util.HibernateUtil;

public class Save {
    public static void main(String[] args) {

        Student student = new Student("Ramesh", "chatty", "ramesh@gmail.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            
            System.out.println("================ Save Start==================");
            session.save(student);
            
            System.out.println("================ Save complete==================");
            System.out.println("================ persist Start==================");
            Student student3 = new Student("ramesh1", "chatty1", "ramesh1@gmail.com");
            session.persist(student3);
            
            
            System.out.println("================ persist end==================");
            
            System.out.println("================ SaveOrUpdate start==================");
            
            Student student2 = new Student("Ramesh2", "chatty2", "ramesh1@gmail.com");
            session.saveOrUpdate(student2);
            
            
            System.out.println("================ SaveOrUpdate end==================");
            
//            student.setLastName("chatty123456");
//            session.save(student);
//            student3.setLastName("abcdef");
//            session.persist(student3);
//            student2.setLastName("chatty123");
//            session.saveOrUpdate(student2);
            
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            students.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}