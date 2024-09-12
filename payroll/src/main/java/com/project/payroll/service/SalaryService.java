package com.project.payroll.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.payroll.exception.InvalidIdException;
import com.project.payroll.model.Employee;
import com.project.payroll.model.Salary;
import com.project.payroll.repository.EmployeeRepository;
import com.project.payroll.repository.SalaryRepository;

@Service
public class SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Salary setSalary(int eid, Salary salary) throws InvalidIdException {
		Optional<Employee> optional = employeeRepository.findById(eid);
		if(optional.isEmpty())
			throw new InvalidIdException("Invalid Id Given");
		
		// Link salary to the employee
        Employee employee = optional.get();
        salary.setEmployee(employee);
        
		return salaryRepository.save(salary);
	}

}
