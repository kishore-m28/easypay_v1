package com.project.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payroll.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
