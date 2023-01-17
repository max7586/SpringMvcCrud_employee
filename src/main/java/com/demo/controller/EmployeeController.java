package com.demo.controller;

import javax.persistence.PostLoad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.model.Employee;
import com.demo.service.EmployeeServices;

@Controller
@RequestMapping
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeServices;
	
	//load add employee form
	@GetMapping("addEmployee")
	public String addEmp() 
	{
		return "AddEmployee";
	}
	
	//Save employee form
	@PostMapping("/insertEmployee")
	public String insertEmployee(@ModelAttribute("insertEmployee") Employee emp)
	{
		employeeServices.addEmp(emp);
		
		return "redirect:/employeeReport"; 
	}
	
	//Load employee data 
	@GetMapping("employeeReport")
	public  String loadEmployee(Model m)
	{
		m.addAttribute("employee", employeeServices.getAllEmp());
		m.addAttribute("title", "Employee Report");
		
		return "EmployeeReport";
	}
	
	//Load edit form
	@GetMapping("/editEmployee/{id}")
	public String loadEditForm(@PathVariable(value="id") Long id, Model m)
	{
		Employee emp = employeeServices.getById(id);
		
		m.addAttribute("employee", emp);
		m.addAttribute("title", "Edit Employee");
		
		return "EditEmployee";
		
	}
	
	//Update form
	@PostMapping("/editEmployee/updateEmployee")
	public String updateEmp(@ModelAttribute("updateEmployee") Employee emp)
	{
		employeeServices.updateEmp(emp);
		return "redirect:/employeeReport";
	}
	
	//Delete employee
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable Long id)
	{
		employeeServices.deleteEmployee(id);
		return "redirect:/employeeReport";
	}
	
}
