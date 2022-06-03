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

import com.example.professor.allocation.rafael.entity.Course;
import com.example.professor.allocation.rafael.service.CourseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(path = "/courses")
public class CourseController {

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@ApiOperation(value ="Find all courses")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)	
	public ResponseEntity<List<Course>> findAll() {
		return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value ="Find by id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 402, message = "Not Found")
	})
	@GetMapping(path="/{course_id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> findById(@PathVariable(name="course_id") Long id) {
		Optional<Course> op = this.courseService.findById(id);
		if (op.isPresent()) {
			return new ResponseEntity<>(op.get(),HttpStatus.OK);
		}
		return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);

	}

	@ApiOperation(value ="Save Course")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
	})
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Course> save(@RequestBody Course course) {
		try {
			System.out.println(">>>>>>" + course);
			Course a = this.courseService.save(course);
			return new ResponseEntity<>(a,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}


	@ApiOperation(value ="Update Course")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK"),
		@ApiResponse(code = 404, message = "Not Found")
	})
	@PutMapping( consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Course> update(@RequestBody Course course) {
		try {
			Course a = this.courseService.save(course);
			if (a != null) {
				return new ResponseEntity<>(a,HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value ="Delete By Course Id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping("/{course_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteById(@PathVariable(name="course_id") Long id) {
		try {
			this.courseService.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}

	@ApiOperation(value ="Delete All Courses")
	@ApiResponses({
		@ApiResponse(code = 200, message = "OK")
		
	})
	@DeleteMapping()
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deleteAll() {
		try {
			this.courseService.deleteAll();
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}


	}

}
