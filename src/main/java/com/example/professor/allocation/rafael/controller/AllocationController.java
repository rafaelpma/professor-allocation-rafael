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

import com.example.professor.allocation.rafael.entity.Allocation;
import com.example.professor.allocation.rafael.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}


	@ApiOperation(value ="Find all allocations")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> list = this.allocationService.findAll();

		return new ResponseEntity<>(list,HttpStatus.OK);

	}

	@ApiOperation(value ="Find by allocation id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 402, message = "Not Found")
	})
	@GetMapping(path="/{allocation_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {
		Optional<Allocation> opAlocation = this.allocationService.findById(id);
		if (opAlocation.isPresent()) {
			return new ResponseEntity<>(opAlocation.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Allocation>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value ="Find by professor id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(path="professor/{professor_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByProfessorId(@PathVariable(name = "professor_id")Long id) {
		List<Allocation> list = this.allocationService.findByProfessorId(id);

		return new ResponseEntity<>(list,HttpStatus.OK);

	}

	@ApiOperation(value ="Find by course id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(path="course/{course_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Allocation>> findByCourseId(@PathVariable(name = "course_id") Long id) {
		List<Allocation> list = this.allocationService.findByCourseId(id);

		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@ApiOperation(value ="Save Allocation")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Allocation> save(@RequestBody Allocation allocation) {
		
		try {
			Allocation a = this.allocationService.save(allocation);
			return new ResponseEntity<>(a,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value ="Update Allocation")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Allocation> update(@RequestBody Allocation allocation) {

		try {
			Allocation a = this.allocationService.update(allocation);
			if (a != null) {
				return new ResponseEntity<>(a,HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

//
//
//	public ResponseEntity<Boolean> hasCollision(Allocation allocation) {
//
//
//
//		return null;
//
//	}


	@ApiOperation(value ="Delete all Allocations")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping("/{allocation_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {

		try {
			this.allocationService.deleteById(id);
				
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@ApiOperation(value ="Delete all Allocations")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {

		try {
			this.allocationService.deleteAll();
				
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}





}
