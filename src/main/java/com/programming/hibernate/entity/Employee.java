package com.programming.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "first_name",nullable = false, length = 55)
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "salary")
	private Double salary;

	// Getters & Setters

}