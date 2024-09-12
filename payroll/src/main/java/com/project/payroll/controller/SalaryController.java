package com.project.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.payroll.dto.MessageDto;
import com.project.payroll.exception.InvalidIdException;
import com.project.payroll.model.Salary;
import com.project.payroll.service.SalaryService;
 

@RestController
public class SalaryController {
	
	@Autowired
	private SalaryService salaryService;
	
	@PostMapping("/employee/salary/set/{eid}")
	public ResponseEntity<?> setSalary(@PathVariable int eid, @RequestBody Salary salary, MessageDto dto) {
		try {
			salary = salaryService.setSalary(eid, salary);
			return ResponseEntity.ok(salary);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
			return ResponseEntity.badRequest().body(dto);
		}
	}
	
}
