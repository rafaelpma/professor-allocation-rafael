package com.example.professor.allocation.rafael.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.professor.allocation.rafael.entity.Allocation;
import com.example.professor.allocation.rafael.repository.AllocationRepository;
import com.example.professor.allocation.rafael.repository.CourseRepository;
import com.example.professor.allocation.rafael.repository.ProfessorRepository;

@Service
public class AllocationService {
	
			
	
	private final AllocationRepository allocationRepository;
	
	private final CourseRepository courseRepository;
	
	private final ProfessorRepository professorRepository;
	
	
	public AllocationService(AllocationRepository allocationRepository,
			CourseRepository courseRepository, ProfessorRepository professorRepository) {
		super();
		this.allocationRepository = allocationRepository;
		this.courseRepository = courseRepository;
		this.professorRepository = professorRepository;
	}
	
	public List<Allocation> findAll() {
		 return this.allocationRepository.findAll();
	}
	
 
    public Optional<Allocation> findById(Long id) {
    	return this.allocationRepository.findById(id);

    }

    
    public void findByProfessorId(Long id) {
    	this.allocationRepository.findByProfessorId(id);
    	
    }

    
    public void findByCourseId(Long id) {
    	this.allocationRepository.findByCourseId(id);
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
		
		
		/**
		 * Incluir regras de negócio de alocação
		 */
		
		
		return this.allocationRepository.save(allocation);
	}


    
    public void deleteById(Long id) {

    	this.allocationRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.allocationRepository.deleteAll();
    	
    }

	

}
