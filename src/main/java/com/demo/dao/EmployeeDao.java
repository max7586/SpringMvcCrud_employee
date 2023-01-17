package com.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

import javax.transaction.Transactional;

import com.demo.model.Employee;


@Component
public class EmployeeDao {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	
	//Add employee
	@Transactional
	public void addEmployee(Employee emp)
	{
		hibernateTemplate.save(emp);
	}
	
	//Get all employee
	public List<Employee> getAllEmp()
	{
		return hibernateTemplate.loadAll(Employee.class); 
	}
	
	//Get employee by id
	public Employee getEmpId(Long id)
	{
		return hibernateTemplate.get(Employee.class, id);
	}
	
	//update employee 
	@Transactional
	public void updateEmp(Employee emp)
	{
		hibernateTemplate.update(emp);
	}
	
	//delete employee
	@Transactional
	public void deleteEmp(Long id)
	{
		hibernateTemplate.delete(hibernateTemplate.load(Employee.class, id));
	}
}
