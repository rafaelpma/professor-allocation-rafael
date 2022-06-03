package com.example.professor.allocation.rafael.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.professor.allocation.rafael.entity.Department;
import com.example.professor.allocation.rafael.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
			
	
	private final DepartmentRepository departmentRepository;
	
	
	
	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}
	
	public List<Department> findAll() {
		 return this.departmentRepository.findAll();
	}
	
 
    public Optional<Department> findById(Long id) {
    	return this.departmentRepository.findById(id);

    }


    public Department save(Department department) {
    	department.setId(null);
    	return saveInternal(department);
    }
    
    public Department update(Department department) {
    	if (department.getId() != null) {
    		return saveInternal(department);
    	} else {
    		return null;
    	}
    }

	private Department saveInternal(Department department) {

		return this.departmentRepository.save(department);
	}


    
    public void deleteById(Long id) {

    	this.departmentRepository.deleteById(id);
    	
    }

    
    public void deleteAll() {
    	
    	this.departmentRepository.deleteAll();
    	
    }

	

}
