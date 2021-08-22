package com.javafsd.brijesh.departmentservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.javafsd.brijesh.departmentservice.entity.Department;
import com.javafsd.brijesh.departmentservice.service.DepartmentService;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DepartmentService departmentService;
	
	private Department departmentResponse;
	
	@BeforeEach
	public void setUp() {
		departmentResponse =
				Department.builder()
				.departmentName("Transport")
				.departmentCode("T001")
				.departmentAddress("Noida")
				.departmentId(1l)
				.build();
	}
	
	@Test
	@DisplayName("Test to save Department")
	public void whenDepartmentPayload_thenSaveDepartment() throws Exception {
		Department departmentRequest =
				Department.builder()
				.departmentName("Transport")
				.departmentCode("T001")
				.departmentAddress("Noida")
				.build();
		
		Mockito.when(departmentService.save(departmentRequest))
				.thenReturn(departmentResponse);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/departments/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n"
						+ "	\"departmentName\":\"Transport\",\r\n"
						+ "	\"departmentAddress\": \"Noida\",\r\n"
						+ "	\"departmentCode\": \"T001\"\r\n"
						+ "}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	@DisplayName("Test to fetch Department by Department Id")
	public void getDepartmentById() throws Exception {
		Mockito.when(departmentService.findByDepartmentId(1L))
			.thenReturn(departmentResponse);
		mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.departmentId").value(departmentResponse.getDepartmentId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.departmentName").value(departmentResponse.getDepartmentName()));
		
	}
}
