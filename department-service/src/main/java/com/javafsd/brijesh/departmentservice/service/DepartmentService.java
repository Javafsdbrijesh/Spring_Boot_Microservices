package com.javafsd.brijesh.departmentservice.service;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javafsd.brijesh.departmentservice.entity.Department;
import com.javafsd.brijesh.departmentservice.exception.DepartmentNotFoundException;
import com.javafsd.brijesh.departmentservice.repository.DepartmentRepository;

@Service
public class DepartmentService {

	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	private DepartmentRepository departmentRepository;

	public Department save(Department department) {
		Department dept = departmentRepository.save(department);

		LOGGER.info("Department saved successfully : " + dept);

		return dept;

	}

	/*
	 * public Department findByDepartmentId(Long departmentId) throws
	 * DepartmentNotFoundException { Optional<Department> department =
	 * departmentRepository.findById(departmentId);
	 * 
	 * if(!department.isPresent()) { throw new
	 * DepartmentNotFoundException("Department does not exist for this Id"); }
	 * return department.get(); }
	 */
	
	public Department findByDepartmentId(Long departmentId){
		Department department = departmentRepository.findByDepartmentId(departmentId);
		if(department ==null) {
			throw new DepartmentNotFoundException("Department dosent exist for this");
		}
		return department;
	}

	public void deleteDepartmentById(Long departmentId) {
		departmentRepository.deleteById(departmentId);
	}

	public List<Department> findAll() {
		return departmentRepository.findAll();
	}

	public Department updateDepartment(Long departmentId, Department department) {

		Department deptDatabase = departmentRepository.findById(departmentId).get();

		if(Objects.nonNull(department.getDepartmentName()) && 
				!"".equalsIgnoreCase(department.getDepartmentName())) {
			deptDatabase.setDepartmentName(department.getDepartmentName());
		}
		if(Objects.nonNull(department.getDepartmentAddress()) && 
				!"".equalsIgnoreCase(department.getDepartmentAddress())) {
			deptDatabase.setDepartmentAddress(department.getDepartmentAddress());
		}
		if(Objects.nonNull(department.getDepartmentCode()) && 
				!"".equalsIgnoreCase(department.getDepartmentCode())) {
			deptDatabase.setDepartmentCode(department.getDepartmentCode());
		}

		return departmentRepository.save(deptDatabase);
	}

}
