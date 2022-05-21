package com.example.professor.allocation.rafael.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

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
		System.out.println("findAll() - init");
		List<Allocation> lista = allocationService.findAll();
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

		Optional<Allocation> op = allocationService.findById(2l);

		printOptionalAllocation(op);

		Optional<Allocation> op2 = allocationService.findById(200l);

		printOptionalAllocation(op2);
		System.out.println("findById() - end");

	}

	private void printOptionalAllocation(Optional<Allocation> op) {
		if (op.isPresent()) {
			Allocation a = op.get();
			System.out.println(a);
		} else {
			System.out.println("Allocation não Encontrado!!!");
		}
	}

	@Test
	public void findByProfessorId() {
		List<Allocation> list = allocationService.findByProfessorId(1l);
		
		assertFalse(list.isEmpty());

	}

	@Test
	public void findByCourseId() {
		List<Allocation> list = allocationService.findByCourseId(1l);
		
		assertFalse(list.isEmpty());
	}

	@Test
	public void save_create() throws ParseException {
		System.out.println("save_create() - init");

		Allocation allocation = new Allocation();

		allocation.setCourseId(1l);
		allocation.setDayOfWeek(DayOfWeek.FRIDAY);
		allocation.setEndHour(sdf.parse("10:00-0300"));
		allocation.setStartHour(sdf.parse("12:00-0300"));
		allocation.setId(4l);
		allocation.setProfessorId(1l);


		allocationService.save(allocation);

		System.out.println("save_create() - end");

	}

	@Test
	public void save_update() throws ParseException {
		System.out.println("save_update() - init");
		Optional<Allocation> op = allocationService.findById(2l);
		if (op.isPresent()) {
			Allocation allocation = op.get();
			allocation.setDayOfWeek(DayOfWeek.SATURDAY);
			allocationService.save(allocation);

			printOptionalAllocation(op);

		}
		System.out.println("save_update() - end");



	}

	@Test
	public void deleteById() {
		System.out.println("deleteById() - init");
		Optional<Allocation> op = allocationService.findById(4L);
		if (op.isPresent()) {
			findAll();
			allocationService.deleteById(4l);
			findAll();		
		}

		System.out.println("deleteById() - end");

	}

	@Test
	public void deleteAll() {

		System.out.println("deleteAll() - init");
		findAll();
		allocationService.deleteAll();
		findAll();
		System.out.println("deleteAll() - end");

	}
}
