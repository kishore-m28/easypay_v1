package com.project.payroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.payroll.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
