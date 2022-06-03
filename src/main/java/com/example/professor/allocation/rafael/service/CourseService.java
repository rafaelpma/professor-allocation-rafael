package com.example.professor.allocation.rafael.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.professor.allocation.rafael.entity.Course;
import com.example.professor.allocation.rafael.repository.CourseRepository;

@Service
public class CourseService {
	
			
	
	private final CourseRepository courseRepository;
	
	
	
	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}
	
	public List<Course> findAll() {
		 return this.courseRepository.findAll();
	}
	
 
    public Optional<Course> findById(Long id) {
    	return this.courseRepository.findById(id);

    }


    public Course save(Course course) {
    	course.setId(null);
    	return saveInternal(course);
    }
    
    public Course update(Course course) {
    	if (course.getId() != null) {
    		return saveInternal(course);
    	} else {
    		return null;
    	}
    }

	private Course saveInternal(Course course) {

		return this.courseRepository.save(course);
	}


    
    public void deleteById(Long id) {

    	this.courseRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.courseRepository.deleteAll();
    	
    }

	

}
