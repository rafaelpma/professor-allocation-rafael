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

import com.example.professor.allocation.rafael.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServicesTest {

	private static final String HUMANAS_TESTE = "Humanas Teste";
	private static final String EXATAS_TESTE = "Exatas Teste";

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	DepartmentService departmentServices;

	@Test
	public void findAll() {
		System.out.println("findAll() - init");
		List<Department> lista = departmentServices.findAll();
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

		Optional<Department> op = departmentServices.findById(2l);

		printOptionalDepartment(op);

		Optional<Department> op2 = departmentServices.findById(1l);

		printOptionalDepartment(op2);
		System.out.println("findById() - end");

	}

	private void printOptionalDepartment(Optional<Department> op) {
		if (op.isPresent()) {
			Department a = op.get();
			System.out.println(a);
		} else {
			System.out.println("Department not found!!!");
		}
	}


	@Test
	public void save_create() throws ParseException {
		System.out.println("save_create() - init");

		Department department = new Department();
		department.setName(HUMANAS_TESTE + new Date());
		departmentServices.save(department);

		System.out.println("save_create() - end");

	}

	@Test
	public void save_update() throws ParseException {
		System.out.println("save_update() - ini");
		List<Department> list = departmentServices.findAll();
		list.forEach(d -> {
			if (d.getName().startsWith(HUMANAS_TESTE)) {
				
				d.setName(EXATAS_TESTE + new Date());
				departmentServices.save(d);

				Optional<Department> op2 = departmentServices.findById(d.getId());

				assertTrue(d.getName().equals(op2.get().getName()));
			}
		});
		System.out.println("save_update()- end");



	}

	@Test
	public void deleteById() {
		System.out.println("deleteById() - ini");
		List<Department> list = departmentServices.findAll();

		boolean flag = false;
		for (Department d : list) {
			if (d.getName().startsWith(HUMANAS_TESTE)) {
				departmentServices.deleteById(d.getId());
				flag = true;
			}

		}

		assertTrue(flag);

		System.out.println("deleteById() - end");

	}


	//@Test
	public void deleteAll() {

		System.out.println("deleteAll() - init");

		departmentServices.deleteAll();

		System.out.println("deleteAll() - end");

	}
}
