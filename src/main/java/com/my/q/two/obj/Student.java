package com.my.q.two.obj;

import java.util.Objects;

public class Student {

	private int id;
	private String firstName;
	private double gpa;

	public Student() {

	}

	/**
	 * Constructor to create Student object
	 * @param id
	 * @param firstName
	 * @param gpa
	 */
	public Student(int id, String firstName, double gpa) {
		this.id = id;
		this.firstName = firstName;
		this.gpa = gpa;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * override equals for junit test
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return id == student.id && Objects.equals(firstName, student.firstName) && Objects.equals(gpa, student.gpa);
	}

	/**
	 * override hashcode to provide consistent hashcode for object with same content for junit test
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, gpa);
	}
}
