package com.example.professor.allocation.rafael.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "professor")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "cpf", nullable = false, unique = true, length = 14)
	private String cpf;
	
	@Column(name = "department_id", nullable = false)
	private Long departmentId;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false, insertable = false, updatable = false)
    private Department department;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany(mappedBy = "department")
	private List<Professor> professors;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public List<Professor> getProfessors() {
		return professors;
	}
	public void setProfessors(List<Professor> professors) {
		this.professors = professors;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id: ");
		sb.append(this.id);
		sb.append(", nome: ");
		sb.append(this.name);
		sb.append(", cpf: ");
		sb.append(this.cpf);
		sb.append(", deprtmentId: ");
		sb.append(this.departmentId);
		return sb.toString();
	}
	
	
}