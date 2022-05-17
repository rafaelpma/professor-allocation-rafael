package com.example.professor.allocation.rafael.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.professor.allocation.rafael.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	
	
}
