package com.example.professor.allocation.rafael.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.professor.allocation.rafael.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationService allocationService;

	@Test
	public void findAll() {
		System.out.println("FIND ALL - INIT");
		List<Allocation> lista = allocationService.findAll();
		if (lista.isEmpty()) {
			System.out.println("LISTA VAZIA!!!!");
		}
		lista.forEach(a -> {
			printAllocation(a);

		});
		System.out.println("FIND ALL - END");

	}

	private void printAllocation(Allocation a) {
		System.out.println("-------------------------------------");
		System.out.println("Id               : " + a.getId());
		System.out.println("Nome do Professor: " + a.getProfessor().getName());
		System.out.println("Curso            : " + a.getCourse().getName());
		System.out.println("Inicio           : " + a.getStartHour());
		System.out.println("Fim              : " + a.getEndHour());
		System.out.println("Dia da Semana    : " + a.getDayOfWeek());
		System.out.println("-------------------------------------");
	}

	@Test
	public void findById() {
		System.out.println("FIND BY ID - INIT");

		Optional<Allocation> op = allocationService.findById(2l);

		printOptionalAllocation(op);

		Optional<Allocation> op2 = allocationService.findById(200l);

		printOptionalAllocation(op2);
		System.out.println("FIND BY ID - END");

	}

	private void printOptionalAllocation(Optional<Allocation> op) {
		if (op.isPresent()) {
			Allocation a = op.get();
			printAllocation(a);
		} else {
			System.out.println("Allocation não Encontrado!!!");
		}
	}

	@Test
	public void findByProfessorId() {

	}

	@Test
	public void findByCourseId() {

	}

	@Test
	public void save_create() throws ParseException {
		System.out.println("SAVE_CREATE - INIT");

		Allocation allocation = new Allocation();

		allocation.setCourseId(1l);
		allocation.setDayOfWeek(DayOfWeek.FRIDAY);
		allocation.setEndHour(sdf.parse("10:00-0300"));
		allocation.setStartHour(sdf.parse("12:00-0300"));
		allocation.setId(4l);
		allocation.setProfessorId(1l);


		allocationService.save(allocation);

		System.out.println("SAVE_CREATE - END");

	}

	@Test
	public void save_update() throws ParseException {
		System.out.println("SAVE_UPDATE - INIT");
		Optional<Allocation> op = allocationService.findById(2l);
		if (op.isPresent()) {
			Allocation allocation = op.get();
			allocation.setDayOfWeek(DayOfWeek.SATURDAY);
			allocationService.save(allocation);

			printOptionalAllocation(op);

		}
		System.out.println("SAVE_UPDATE - END");



	}

	@Test
	public void deleteById() {
		System.out.println("DELETE BY ID - INIT");
		Optional<Allocation> op = allocationService.findById(4L);
		if (op.isPresent()) {
			findAll();
			allocationService.deleteById(4l);
			findAll();		
		}

		System.out.println("DELETE BY ID - END");

	}

	@Test
	public void deleteAll() {

		System.out.println("DELETE ALL - INIT");
		findAll();
		allocationService.deleteAll();
		findAll();
		System.out.println("DELETE ALL - END");

	}
}