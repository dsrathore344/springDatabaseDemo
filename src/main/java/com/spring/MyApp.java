package com.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.bean.Employee;
import com.spring.dao.EmployeeDAO;

public class MyApp {
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		EmployeeDAO emp = context.getBean("employeeDAO", EmployeeDAO.class);
		//List<Employee> empList = emp.getEmployeeList();
		
		//Employee empById = emp.getEmployeeList(2345441);
		
		//emp.addEmployee(2354794, "Alekhya", "Tunku");
		
		
		/*
		 * Employee empUpdate = new Employee("Devendra Rathore",2345441,"Jaipur");
		 * emp.updateEmployee(empUpdate);
		 */
		 
		/* emp.deleteEmployee(2345441); */
		
		/*
		 * Employee e1 = new Employee("Raju", 2345114, "Jaipur"); Employee e2 = new
		 * Employee("Pushpa", 2347548, "Delhi"); Employee e3 = new Employee("Priyanka",
		 * 2658745, "Patana");
		 * 
		 * List<Employee> list = new ArrayList<Employee>(); list.add(e1); list.add(e2);
		 * list.add(e3);
		 * 
		 * int [] effectedRow = emp.addEmployeeList(list); for (int i : effectedRow) {
		 * System.out.println(i); }
		 */
		
		Employee e1 = new Employee("Sindhu", 2345784, "TadePalliGudam");
		int v = emp.create_si(e1);
		System.out.println("Added at id : "+v);
		/*
		 * for (Employee employee : empList) { System.out.println(employee); }
		 */
		 
	}
	
	
}
