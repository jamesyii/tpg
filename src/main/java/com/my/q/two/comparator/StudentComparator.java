package com.my.q.two.comparator;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.my.q.two.obj.Student;

public class StudentComparator implements Comparator<Student> {

	/**
	 * Compare  GPA	 in	 descending	 order followed by first name in alphabetical order then by student id
	 */
	public int compare(Student s1, Student s2) {
		 return new CompareToBuilder().append(s2.getGpa(), s1.getGpa())
							                .append(s1.getFirstName(), s2.getFirstName())
							                .append(s1.getId(), s2.getId()).toComparison();
	}
}