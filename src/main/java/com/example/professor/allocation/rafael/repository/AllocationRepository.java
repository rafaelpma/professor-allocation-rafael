package com.example.professor.allocation.rafael.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.professor.allocation.rafael.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>{
	
	List<Allocation> findByProfessorId(Long professorId);
	
	List<Allocation> findByCourseId(Long courseId);
	
}
