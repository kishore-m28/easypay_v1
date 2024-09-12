package com.project.payroll.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.payroll.exception.InvalidIdException;
import com.project.payroll.model.Employee;
import com.project.payroll.model.UserInfo;
import com.project.payroll.repository.EmployeeRepository;
import com.project.payroll.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Employee addEmployee(Employee employee) {
		//detach user info from employee
		UserInfo userInfo = employee.getUser();
		userInfo.setRole("ROLE_EMPLOYEE");
		
		//encode the password
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		
		userInfo = userRepository.save(userInfo);
		
		//attach it to employee
		employee.setUser(userInfo);

		//save employee
	   return employeeRepository.save(employee);
	} 

	public Employee getById(int eid) throws InvalidIdException{
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		return optional.get();
	}

}
