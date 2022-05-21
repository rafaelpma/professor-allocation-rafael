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


    public Course save(Course allocation) {
    	allocation.setId(null);
    	return saveInternal(allocation);
    }
    
    public Course update(Course allocation) {
    	if (allocation.getId() != null) {
    		return saveInternal(allocation);
    	} else {
    		return null;
    	}
    }

	private Course saveInternal(Course allocation) {

		return this.courseRepository.save(allocation);
	}


    
    public void deleteById(Long id) {

    	this.courseRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.courseRepository.deleteAll();
    	
    }

	

}
