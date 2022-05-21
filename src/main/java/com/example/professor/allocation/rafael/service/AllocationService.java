package com.example.professor.allocation.rafael.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.professor.allocation.rafael.entity.Allocation;
import com.example.professor.allocation.rafael.entity.Course;
import com.example.professor.allocation.rafael.entity.Professor;
import com.example.professor.allocation.rafael.repository.AllocationRepository;

@Service
public class AllocationService {
	
			
	
	private final AllocationRepository allocationRepository;
	
	private final CourseService courseService;
	
	private final ProfessorService professorService;
	
	
	public AllocationService(AllocationRepository allocationRepository,
			CourseService courseRepository, ProfessorService professorRepository) {
		super();
		this.allocationRepository = allocationRepository;
		this.courseService = courseRepository;
		this.professorService = professorRepository;
	}
	
	public List<Allocation> findAll() {
		 return this.allocationRepository.findAll();
	}
	
 
    public Optional<Allocation> findById(Long id) {
    	return this.allocationRepository.findById(id);

    }

    
    public List<Allocation> findByProfessorId(Long id) {
    	return this.allocationRepository.findByProfessorId(id);
    	
    }

    
    public List<Allocation> findByCourseId(Long id) {
    	return this.allocationRepository.findByCourseId(id);
    }

    
    public Allocation save(Allocation allocation) {
    	allocation.setId(null);
    	return saveInternal(allocation);
    }
    
    public Allocation update(Allocation allocation) {
    	if (allocation.getId() != null) {
    		return saveInternal(allocation);
    	} else {
    		return null;
    	}
    }

    private Allocation saveInternal(Allocation allocation) {
    	if (hasCollision(allocation) || isEndHourGreaterThanStartHour(allocation)) {
    		throw new RuntimeException("Invalid Allocation!");
    	}
    	
    	allocation = allocationRepository.save(allocation);
    	Optional<Professor> opProf = professorService.findById(allocation.getProfessorId());
    	Optional<Course> opCourse = courseService.findById(allocation.getCourseId());

    	allocation.setProfessor(opProf.get());
    	allocation.setCourse(opCourse.get());

    	return allocation;
    }
    
    public boolean hasCollision(Allocation allocation) {
    	
    	
    	List<Allocation> list = allocationRepository.findByProfessorId(allocation.getProfessorId());
    	for (Allocation a : list) {
    		if (!a.getId().equals(allocation.getId()) &&
    				a.getDayOfWeek().equals(allocation.getDayOfWeek()) &&
    				a.getStartHour().compareTo(allocation.getEndHour()) < 0 &&
    				allocation.getStartHour().compareTo(a.getEndHour()) < 0) {
    			return true;
    		}
    	}
    	return false;
    	
    }

    boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && 
        		allocation.getStartHour() != null && 
        		allocation.getEndHour() != null && 
        		allocation.getEndHour().compareTo(allocation.getStartHour()) > 0;
    }

    
    public void deleteById(Long id) {

    	this.allocationRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.allocationRepository.deleteAll();
    	
    }

	

}
