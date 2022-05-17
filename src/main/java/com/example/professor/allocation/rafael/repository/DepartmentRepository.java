package com.example.professor.allocation.rafael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.professor.allocation.rafael.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	
	
	
}
