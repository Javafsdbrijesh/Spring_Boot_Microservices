package com.javafsd.brijesh.userservice.entity;

import com.javafsd.brijesh.userservice.vo.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateView {
	private User user;
	private Department department;

}
