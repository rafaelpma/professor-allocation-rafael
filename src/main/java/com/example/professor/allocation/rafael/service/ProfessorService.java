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


    public Professor save(Professor professor) {
    	professor.setId(null);
    	return saveInternal(professor);
    }
    
    public Professor update(Professor professor) {
    	if (professor.getId() != null) {
    		return saveInternal(professor);
    	} else {
    		return null;
    	}
    }

	private Professor saveInternal(Professor professor) {

		return this.professorRepository.save(professor);
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
