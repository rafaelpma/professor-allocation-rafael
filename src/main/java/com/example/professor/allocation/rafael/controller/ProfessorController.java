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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.professor.allocation.rafael.entity.Professor;
import com.example.professor.allocation.rafael.service.ProfessorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		super();
		this.professorService = professorService;
	}
	
	
	@ApiOperation(value ="Find all professors")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<List<Professor>> findAll() {
		return new ResponseEntity<>(professorService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value ="Find by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 402, message = "Not Found")
	})
	@GetMapping(path="/{professor_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> findById(@PathVariable(name="professor_id") Long id) {
		Optional<Professor> op = this.professorService.findById(id);
		if (op.isPresent()) {
			return new ResponseEntity<>(op.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Professor>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value ="Save Professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Professor> save(@RequestBody Professor professor) {
		try {
			System.out.println(">>>>>>" + professor);
			Professor a = this.professorService.save(professor);
			return new ResponseEntity<>(a,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@ApiOperation(value ="Update Professor")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Professor> update(@RequestBody Professor professor) {
		try {
			Professor a = this.professorService.save(professor);
			if (a != null) {
				return new ResponseEntity<>(a,HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value ="Delete By Professor Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping("/{professor_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name="professor_id") Long id) {
		try {
			this.professorService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}

	@ApiOperation(value ="Delete All Professors")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		try {
			this.professorService.deleteAll();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}


}
