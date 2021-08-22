package com.javafsd.brijesh.userservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javafsd.brijesh.userservice.entity.ResponseTemplateView;
import com.javafsd.brijesh.userservice.entity.User;
import com.javafsd.brijesh.userservice.repository.UserRepository;
import com.javafsd.brijesh.userservice.vo.Department;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class UserService {

	private static final String USERSERVICE = "userService";
	private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	Long fallbackUserId;
	int attempts=1;

	public User saveUser(User user) {
		return userRepository.save(user);
	}


	@Retry(name = USERSERVICE, fallbackMethod = "fallback_getUserWithDepartmentId")
	public ResponseTemplateView getUserWithDepartmentId(Long userId) {	
		LOGGER.info("Request Attempt "+ attempts++);
		fallbackUserId = userId;
		ResponseTemplateView view = new ResponseTemplateView();
		User userDB = userRepository.findByUserId(userId);
		Department department =
				restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+userDB.getDepartmentId(), Department.class);
		view.setUser(userDB);
		view.setDepartment(department);
		return view;
	}

	public ResponseTemplateView fallback_getUserWithDepartmentId(Exception exception) {		
		LOGGER.info("Fallback Method called ");
		ResponseTemplateView view = new ResponseTemplateView();
		User userDB = userRepository.findByUserId(fallbackUserId);
		view.setUser(userDB);
		return view;
	}

}
