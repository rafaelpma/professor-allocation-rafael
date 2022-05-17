package com.example.professor.allocation.rafael.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.example.professor.allocation.rafael.entity.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class CourseRepositoryTest {

	 private static final String PORTUGUES_TESTE = "Português Teste";
	 private static final String MATEMATICA_TESTE = "Matematica Teste";

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	    @Autowired
	    CourseRepository courseRepository;

	    @Test
	    public void findAll() {
	    	System.out.println("FIND ALL - INIT");
	    	List<Course> lista = courseRepository.findAll();
	    	if (lista.isEmpty()) {
	    		System.out.println("LISTA VAZIA!!!!");
	    	}
	    	lista.forEach(a -> {
	    		printCourse(a);
	    		
	    	});
	    	System.out.println("FIND ALL - END");

	    }

		private void printCourse(Course a) {
			System.out.println("-------------------------------------");
			System.out.println("Id               : " + a.getId());
			System.out.println("Nome             : " + a.getName());
			System.out.println("-------------------------------------");
		}

	    @Test
	    public void findById() {
	    	System.out.println("FIND BY ID - INIT");
	    	
	    	Optional<Course> op = courseRepository.findById(2l);
	    	
	    	printOptionalCourse(op);
	    	
	    	Optional<Course> op2 = courseRepository.findById(1l);
	    	
	    	printOptionalCourse(op2);
	    	System.out.println("FIND BY ID - END");
	    	
	    }

		private void printOptionalCourse(Optional<Course> op) {
			if (op.isPresent()) {
	    		Course a = op.get();
	    		printCourse(null);
	    	} else {
	    		System.out.println("Course not found!!!");
	    	}
		}


	    @Test
	    public void save_create() throws ParseException {
	    	System.out.println("SAVE_CREATE - INIT");
	    	
	    	Course course = new Course();
	    	course.setName(PORTUGUES_TESTE + new Date());
	    	courseRepository.save(course);
	    	
	    	System.out.println("SAVE_CREATE - END");
	    	
	    }

	    @Test
	    public void save_update() throws ParseException {
	    	System.out.println("SAVE_UPDATE - INIT");
	    	Optional<Course> op = courseRepository.findById(2l);
	    	if (op.isPresent()) {
	    		Course course = op.get();
	    		course.setName(MATEMATICA_TESTE + new Date());
	    		courseRepository.save(course);

	    		Optional<Course> op2 = courseRepository.findById(2l);

	    		assertTrue(course.getName().equals(op2.get().getName()));
	    	}
	    	System.out.println("SAVE_UPDATE - END");
	    	
	    	
	    	
	    }

	    @Test
	    public void deleteById() {
	    	System.out.println("DELETE BY ID - INIT");
	    	List<Course> list = courseRepository.findAll();
	    	
	    	boolean flag = false;
	    	for (Course c : list) {
	    		if (c.getName().startsWith(PORTUGUES_TESTE)) {
	    			courseRepository.deleteById(c.getId());
	    			flag = true;
	    		}
			
	    	}
	    	
	    	assertTrue(flag);

	    	System.out.println("DELETE BY ID - END");
	    	
	    }
	    
	    
	    //@Test
	    public void deleteAll() {
	    	
	    	System.out.println("DELETE ALL - INIT");
	    	
	    	courseRepository.deleteAll();
	    	
	    	System.out.println("DELETE ALL - END");
	    	
	    }
	}
