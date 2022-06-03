package com.example.professor.allocation.rafael.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.professor.allocation.rafael.entity.Department;
import com.example.professor.allocation.rafael.service.DepartmentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
	
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService) {
		super();
		this.departmentService = departmentService;
	}
	
	
	@ApiOperation(value ="Find all departments")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<List<Department>> findAll() {
		return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value ="Find by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 402, message = "Not Found")
	})
	@GetMapping(path="/{department_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> findById(@PathVariable(name="department_id") Long id) {
		Optional<Department> op = this.departmentService.findById(id);
		if (op.isPresent()) {
			return new ResponseEntity<>(op.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value ="Save Department")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Department> save(@RequestBody Department department) {
		try {
			System.out.println(">>>>>>" + department);
			Department a = this.departmentService.save(department);
			return new ResponseEntity<>(a,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@ApiOperation(value ="Update Department")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Department> update(@RequestBody Department department) {
		try {
			Department a = this.departmentService.save(department);
			if (a != null) {
				return new ResponseEntity<>(a,HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value ="Delete By Department Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping("/{department_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name="department_id") Long id) {
		try {
			this.departmentService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}

	@ApiOperation(value ="Delete All Departments")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		try {
			this.departmentService.deleteAll();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}

}
