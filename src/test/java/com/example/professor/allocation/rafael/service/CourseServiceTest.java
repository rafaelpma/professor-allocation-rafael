package com.example.professor.allocation.rafael.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.professor.allocation.rafael.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	private static final String PORTUGUES_TESTE = "Português Teste";
	private static final String MATEMATICA_TESTE = "Matematica Teste";

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	CourseService courseService;

	@Test
	public void findAll() {
		System.out.println("findAll() -init");
		List<Course> lista = courseService.findAll();
		if (lista.isEmpty()) {
			System.out.println("LISTA VAZIA!!!!");
		}
		lista.forEach(a -> {
			System.out.println(a);

		});
		System.out.println("findAll() - end");

	}


	@Test
	public void findById() {
		System.out.println("findById() - init");

		Optional<Course> op = courseService.findById(2l);

		printOptionalCourse(op);

		Optional<Course> op2 = courseService.findById(1l);

		printOptionalCourse(op2);
		System.out.println("findById() - end");

	}

	private void printOptionalCourse(Optional<Course> op) {
		if (op.isPresent()) {
			Course a = op.get();
			System.out.println(a);
		} else {
			System.out.println("Course not found!!!");
		}
	}


	@Test
	public void save_create() throws ParseException {
		System.out.println("save_create() - init");

		Course course = new Course();
		course.setName(PORTUGUES_TESTE + new Date());
		courseService.save(course);

		System.out.println("save_create() - end");

	}

	@Test
	public void save_update() throws ParseException {
		System.out.println("save_update() - init");
		List<Course> list = courseService.findAll();
		
		list.forEach(c -> {
			if (c.getName().startsWith(PORTUGUES_TESTE)) {
				c.setName(MATEMATICA_TESTE + new Date());
				c = courseService.update(c);
				
				Optional<Course> op2 = courseService.findById(c.getId());

				assertTrue(c.getName().equals(op2.get().getName()));
			}
		});
		
		
		System.out.println("save_update() - end");



	}

	@Test
	public void deleteById() {
		System.out.println("deleteById() - init");
		List<Course> list = courseService.findAll();

		boolean flag = false;
		for (Course c : list) {
			if (c.getName().startsWith(MATEMATICA_TESTE)) {
				courseService.deleteById(c.getId());
				flag = true;
			}

		}

		assertTrue(flag);

		System.out.println("deleteById() - end");

	}


	//@Test
	public void deleteAll() {

		System.out.println("deleteAll() - init");

		courseService.deleteAll();

		System.out.println("deleteAll() - end");

	}
}
