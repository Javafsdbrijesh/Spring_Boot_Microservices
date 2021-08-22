package com.javafsd.brijesh.departmentservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javafsd.brijesh.departmentservice.entity.Department;
import com.javafsd.brijesh.departmentservice.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/departments")
@Slf4j
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class); 
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
	
		LOGGER.info("Inside save Department method");
		return departmentService.save(department);
		
	}
	
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable("id") Long departmentId){
		LOGGER.info("Inside get Department method");
		return departmentService.findByDepartmentId(departmentId);
		
	}
	
//	@GetMapping("/{id}")
//	public DepartmentView getDepartmentById(@PathVariable("id") Long departmentId) {
//		LOGGER.info("Inside get Department method");
//		//log.info("inside get departments method");
//		Department dept = departmentService.findByDepartmentId(departmentId);
//		DepartmentView deptView = new DepartmentView();
//		deptView.setDepartmentName(dept.getDepartmentName());
//		return deptView;
//		
//	}
//	
	@GetMapping("/")
	public List<Department> getAllDepartments(){
		log.info("inside find all department method");
		return departmentService.findAll();
		
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
		log.info("inside delete department method");
		departmentService.deleteDepartmentById(departmentId);
		return "Delete department record was successful";
		
	}
	
	@PutMapping("/{id}")
	public Department updateDepartment(@PathVariable("id") Long departmentId,
										@RequestBody Department department) {
		return departmentService.updateDepartment(departmentId, department);
		
	}

}
