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

import com.example.professor.allocation.rafael.entity.Department;
import com.example.professor.allocation.rafael.entity.Professor;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	 private static final String PROFESSOR1_TESTE = "Professor 1 Teste";
	 private static final String PROFESSOR2_TESTE = "Professor 2 Teste";

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	    @Autowired
	    ProfessorRepository professorRepository;
	    
	    @Autowired
	    DepartmentRepository departmentRepository;


	    @Test
	    public void findAll() {
	    	System.out.println("findAll() - init");
	    	List<Professor> lista = professorRepository.findAll();
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
	    	
	    	Optional<Professor> op = professorRepository.findById(2l);
	    	
	    	printOptionalProfessor(op);
	    	
	    	Optional<Professor> op2 = professorRepository.findById(1l);
	    	
	    	printOptionalProfessor(op2);
	    	System.out.println("findById() - end");
	    	
	    }

		private void printOptionalProfessor(Optional<Professor> op) {
			if (op.isPresent()) {
	    		Professor a = op.get();
	    		System.out.println(a);
	    	} else {
	    		System.out.println("Professor not found!!!");
	    	}
		}


	    @Test
	    public void save_create() throws ParseException {
	    	System.out.println("save_create() - init");
	    	Department department = new Department();
	    	department.setName("TESTE " + new Date());
	    	
	    	department = departmentRepository.save(department);
	    	
	    	Professor professor = new Professor();
	    	professor.setCpf("1212121212");;
	    	professor.setDepartmentId(department.getId());
	    	
	    	professor.setName(PROFESSOR1_TESTE + new Date());
	    	professorRepository.save(professor);
	    	
	    	System.out.println("save_create() - end");
	    	
	    }

	    @Test
	    public void save_update() throws ParseException {
	    	System.out.println("save_update() - ini");
	    	Optional<Professor> op = professorRepository.findById(2l);
	    	if (op.isPresent()) {
	    		Professor professor = op.get();
	    		professor.setName(PROFESSOR2_TESTE + new Date());
	    		professorRepository.save(professor);

	    		Optional<Professor> op2 = professorRepository.findById(2l);

	    		assertTrue(professor.getName().equals(op2.get().getName()));
	    	}
	    	System.out.println("save_update()- end");
	    	
	    	
	    	
	    }

	    @Test
	    public void deleteById() {
	    	System.out.println("deleteById() - ini");
	    	List<Professor> list = professorRepository.findAll();
	    	
	    	boolean flag = false;
	    	for (Professor d : list) {
	    		if (d.getName().startsWith(PROFESSOR1_TESTE)) {
	    			professorRepository.deleteById(d.getId());
	    			flag = true;
	    		}
			
	    	}
	    	
	    	assertTrue(flag);

	    	System.out.println("deleteById() - end");
	    	
	    }
	    
	    
	    //@Test
	    public void deleteAll() {
	    	
	    	System.out.println("deleteAll() - init");
	    	
	    	professorRepository.deleteAll();
	    	
	    	System.out.println("deleteAll() - end");
	    	
	    }
	}
