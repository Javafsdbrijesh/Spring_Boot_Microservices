package com.javafsd.brijesh.departmentservice.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.javafsd.brijesh.departmentservice.entity.Department;

@DataJpaTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private TestEntityManager testEntityManager;
	
	@BeforeEach
	void setUp(){
		Department department = 
				Department.builder()
				.departmentName("Admin")
				.departmentAddress("Noida")
				.departmentCode("A002")
				.build();
		testEntityManager.persist(department);
		
	}
	
	@Test
	public void whenFindByDepartmentId_thenDepartmentShouldFound() {
		Department department = departmentRepository.findByDepartmentId(1L);
		assertEquals(department.getDepartmentId(), 1L);
	}

}
