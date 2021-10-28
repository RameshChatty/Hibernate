package com.programming.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.programming.hibernate.entity.Student;
import com.programming.hibernate.util.HibernateUtil;

public class QueryAndCriteria {

	public static void main(String[] args) {

		List<Student> userUsingHql = getUserUsingHql();
		userUsingHql.stream().forEach(System.out::println);

		List<Student> userUsingCriteria = getUserUsingCriteria();
		userUsingCriteria.stream().forEach(System.out::println);
	}

	private static List<Student> getUserUsingHql() {
		
		List<Student> list = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			org.hibernate.query.Query createQuery = session.createQuery("From Student s");
			list = createQuery.list();
			transaction.commit();
			
		} catch (Exception e) {
			
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		return list;
	}

	private static List<Student> getUserUsingCriteria() {
		List<Student> list = new ArrayList<>();
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			Criteria createCriteria = session.createCriteria(Student.class);
			list = createCriteria.list();
			transaction.commit();
			
		} catch (Exception e) {
			
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			
		}
		return list;
	}

}
