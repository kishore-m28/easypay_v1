package com.project.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.payroll.model.UserInfo;
import com.project.payroll.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/user/hello")
	public String userHello() {
		//System.out.println("entered");
		return "Hello, User!";
	}

	@PostMapping("/auth/signup")
	public void signup(@RequestBody UserInfo userInfo) {
		userInfo.setRole("ROLE_HR");
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
	}

	@GetMapping("/admin/hello")
	public String adminHello() {
		return "Hello, Admin!";
	}
}
