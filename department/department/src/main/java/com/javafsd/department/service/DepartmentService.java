package com.javafsd.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafsd.department.entity.Department;
import com.javafsd.department.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;


	public Department saveDepartment(Department department) {
		Department departmentResponse = departmentRepository.save(department);
		return departmentResponse;
	}


	public Department findDepartmentById(Long departmentId) {
		return departmentRepository.findByDepartmentId(departmentId);
//		Optional<Department> department = departmentRepository.findById(departmentId);
//		
//		if(!department.isPresent()) {
//			
//			throw new DepartmentNotFoundException("Department not available !!");
//		}
//		
//		return department.get();
	}


}
