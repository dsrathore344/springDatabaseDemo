package com.spring.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class Employee {
	private String empName;
	private int empId;
	private String empHomeTown;
	
	public Employee() {}
	
	// using it at BeanPropertySqlParameterSource
	public Employee(String empName, int empId, String empHomeTown) {
		this.empName = empName;
		this.empId = empId;
		this.empHomeTown = empHomeTown;
	}

	public String getEmpName() {
		return empName;
	}
	
	//@Value("2345441")
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpId() {
		return empId;
	}
	
	//@Value("2345441")
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	public String getEmpHomeTown() {
		return empHomeTown;
	}

	public void setEmpHomeTown(String empHomeTown) {
		this.empHomeTown = empHomeTown;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + ", empHomeTown=" + empHomeTown + "]";
	}

	
	
	
}
