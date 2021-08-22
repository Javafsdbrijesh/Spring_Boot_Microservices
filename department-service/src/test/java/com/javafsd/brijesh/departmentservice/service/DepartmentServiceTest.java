package com.javafsd.brijesh.departmentservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.javafsd.brijesh.departmentservice.entity.Department;
import com.javafsd.brijesh.departmentservice.repository.DepartmentRepository;

@SpringBootTest
class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;

	@MockBean
	private DepartmentRepository departmentRepository;

	@BeforeEach
	void setUp(){
		Department department = 
				Department.builder()
				.departmentName("Admin")
				.departmentId(2L)
				.departmentAddress("Pune")
				.departmentCode("A0001")
				.build();
		Mockito.when(departmentRepository.findByDepartmentId(2L))
		.thenReturn(department);
	}

	@Test
	@DisplayName("findDepartmentById test case")
	public void whenDepartmentID_thenDepartmentShouldFound(){
		Long departmentId = 2L;
		Department departmentFound = departmentService.findByDepartmentId(departmentId);
		assertEquals(departmentId, departmentFound.getDepartmentId());

	}
}
