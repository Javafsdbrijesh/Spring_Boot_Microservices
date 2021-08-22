package com.javafsd.department.service;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.javafsd.department.entity.Department;
import com.javafsd.department.repository.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {
	
	@Autowired
	private DepartmentService departmentService;
	
	@MockBean
	private DepartmentRepository departmentRepository; 

	@BeforeEach
	void setUp(){
		Department department = Department.builder()
				.departmentAddress("Noida")
				.departmentName("HR")
				.departmentCode("007")
				.departmentId(1L)
				.build();
		Mockito.when(departmentRepository.findByDepartmentId(1L))
			.thenReturn(department);
		
	}

	@Test
	@DisplayName("Test to fetch Department by Department ID")
	public void whenValidDepartmentId_thenDepartmentSouldBeFound() {
		Long departmentId = 1L;
		
		Department departmentFound = departmentService.findDepartmentById(departmentId);
		
		assertEquals(departmentId, departmentFound.getDepartmentId());
		
		
	}

}
