package com.example.professor.allocation.rafael.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.professor.allocation.rafael.entity.Professor;
import com.example.professor.allocation.rafael.repository.ProfessorRepository;

@Service
public class ProfessorService {
	
			
	
	private final ProfessorRepository professorRepository;
	
	
	
	public ProfessorService(ProfessorRepository professorRepository) {
		super();
		this.professorRepository = professorRepository;
	}
	
	public List<Professor> findAll() {
		 return this.professorRepository.findAll();
	}
	
 
    public Optional<Professor> findById(Long id) {
    	return this.professorRepository.findById(id);

    }


    public Professor save(Professor allocation) {
    	allocation.setId(null);
    	return saveInternal(allocation);
    }
    
    public Professor update(Professor allocation) {
    	if (allocation.getId() != null) {
    		return saveInternal(allocation);
    	} else {
    		return null;
    	}
    }

	private Professor saveInternal(Professor allocation) {

		return this.professorRepository.save(allocation);
	}


    
    public void deleteById(Long id) {

    	this.professorRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.professorRepository.deleteAll();
    	
    }

    public List<Professor> findByDepartmentId(Long departmentId) {
    	return this.professorRepository.findByDepartmentId(departmentId);
    	
    }
	

}
