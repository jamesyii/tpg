package test.my.q.two;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.my.q.two.comparator.StudentComparator;
import com.my.q.two.obj.Student;

public class StudentComparatorTest {

	private List<Student> studentList;

	@Before
	public void setupData() {
		studentList = new ArrayList<Student>();
		studentList.add(new Student(33, "Tina", 3.68));
		studentList.add(new Student(85, "Louis", 3.85));
		studentList.add(new Student(56, "Samil", 3.75));
		studentList.add(new Student(19, "Samar", 3.75));
		studentList.add(new Student(22, "Lorry", 3.76));
	}
	
	@Test
	public void testSorting() {
		
		Collections.sort(studentList, new StudentComparator());
		
		assertThat(studentList, hasItems(
				new Student(85, "Louis", 3.85),
				new Student(22, "Lorry", 3.76),
				new Student(19, "Samar", 3.75),
				new Student(56, "Samil", 3.75),
				new Student(33, "Tina", 3.68)
        ));
	}
}
