package com.javafsd.department.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafsd.department.entity.Department;
import com.javafsd.department.service.DepartmentService;



@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	private static final Logger  LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		LOGGER.info("inside save department method");
		Department departmentResp = departmentService.saveDepartment(department);
		
		return departmentResp;
	}
	
	@GetMapping("/{id}")
	public Department findDepartmentById(@PathVariable("id") Long departmentId) {
		LOGGER.info("In Method Fetch DepartmentByID");
		//LOGGER.trace("findDepartmentById accesed");
		Department department = departmentService.findDepartmentById(departmentId);
		return department;
	}
	
	
	
}
