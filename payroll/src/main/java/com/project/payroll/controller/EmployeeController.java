package com.project.payroll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.payroll.dto.MessageDto;
import com.project.payroll.exception.InvalidIdException;
import com.project.payroll.model.Employee;
import com.project.payroll.service.EmployeeService;



@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/one/{eid}")
	public ResponseEntity<?> getById(@PathVariable	int eid, MessageDto dto){
		try {
			Employee employee =  employeeService.getById(eid);
			return ResponseEntity.ok(employee);
		} catch (InvalidIdException e) {
			dto.setMsg(e.getMessage());
 			return ResponseEntity.badRequest().body(dto);
		}
	}
}
