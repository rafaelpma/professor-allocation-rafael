package com.example.professor.allocation.rafael.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.professor.allocation.rafael.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	

	List<Professor> findByDepartmentId(Long departmentId);
	
	
}
