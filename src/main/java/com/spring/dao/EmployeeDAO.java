package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bean.Employee;

@Repository
public class EmployeeDAO {
	
	//private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	private SimpleJdbcInsert simpleJdbcInsert;

	public List<Employee> getEmployeeList(){
		List<Employee> empList = new ArrayList<Employee>();
		//MapSqlParameterSource myMap = new MapSqlParameterSource();
		//myMap.addValue("name", "Devendra");
		
	return jdbcTemplate.query("select * from employee", new RowMapper<Employee>() {
		//return jdbcTemplate.query("select * from employee where empName=:name",myMap, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpHomeTown(rs.getString("empHometownm"));
				return emp;
			}
			});
	}
	
	//retrieving single record
	public Employee getEmployeeList(int id) {
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("id", id);
		return jdbcTemplate.queryForObject("select * from employee where empId=:id", myMap, new RowMapper<Employee>() {

			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee emp = new Employee();
				emp.setEmpId(rs.getInt("empId"));
				emp.setEmpName(rs.getString("empName"));
				emp.setEmpHomeTown(rs.getString("empHometownm"));
				return emp;
			}
			
		});
	
	}
	
	//Add employee
	public boolean addEmployee(int id, String name, String hometown) {
		boolean flag = false;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		param.addValue("name", name);
		param.addValue("hometown", hometown);
		
		int effected =  jdbcTemplate.update("insert into employee (empId,empName,empHometownm) value(:id,:name,:hometown)", param);
		if(effected !=0) {
			flag=true;
			System.out.println("Employee with id "+id+" Added successfully");
		}else {
			System.out.println("Some proble while updating data into Employee table");
		}
		return flag;
	}
	
	public boolean updateEmployee(Employee emp) {
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(emp); // taking it using employee class
		boolean flag = false;
		int effected = jdbcTemplate.update("update employee set empName=:empName, empId=:empId, empHometownm=:empHomeTown where empId=:empId", param);
		if(effected !=0) {
			flag=true;
			System.out.println("Employee with id "+emp.getEmpId()+" updated successfully");
		}else {
			System.out.println("Some proble while updating data into Employee table");
		}
		return flag;
	}
	
	//deleting employee using id
	public boolean deleteEmployee(int id) {
		boolean flag = false;
		MapSqlParameterSource myMap = new MapSqlParameterSource();
		myMap.addValue("id", id);
		
		int effected = jdbcTemplate.update("delete from employee where empId=:id", myMap);
		if(effected !=0) {
			flag=true;
			System.out.println("Employee with id "+id+" deleted successfully");
		}else {
			System.out.println("Some proble while deleting data from Employee table");
		}
		return flag;
	}
	
	@Transactional()
	public int[] addEmployeeList(List<Employee> emp) {
		int[] rowEffected;
		
		ArrayList<MapSqlParameterSource> paramList = new ArrayList<MapSqlParameterSource>();
		for (Employee e : emp) {
			MapSqlParameterSource myMap = new MapSqlParameterSource();
			myMap.addValue("id", e.getEmpId());
			myMap.addValue("name", e.getEmpName());
			myMap.addValue("home", e.getEmpHomeTown());
			paramList.add(myMap);
		}
		MapSqlParameterSource[] batchParams = new MapSqlParameterSource[paramList.size()]; 
		paramList.toArray(batchParams);
		 //Or
		//SqlParameterSource[] batchParams2 = SqlParameterSourceUtils.createBatch(emp.toArray());
		rowEffected = jdbcTemplate.batchUpdate("insert into employee (empId, empName, empHometownm) value(:id,:name,:home)", batchParams);
		
		
		return rowEffected;
	}
	
	public int create_si(Employee emp) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(emp);
		Number rowEffected = simpleJdbcInsert.executeAndReturnKey(param);
		return rowEffected.intValue();
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		//this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("employee");
		/* to get with key */
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("employee").usingGeneratedKeyColumns("id");
		
	}
}
