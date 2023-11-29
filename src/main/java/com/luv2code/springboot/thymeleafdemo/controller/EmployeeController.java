package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees =employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "Employee/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String addEmployeePage(Model theModel) {

		Employee employee = new Employee();

		// add to the spring model
		theModel.addAttribute("employee", employee);

		return "Employee/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee , BindingResult bindingResult ) {

		//Check if the data hava errors:
		if(bindingResult.hasErrors())
		{
			return "Employee/employee-form";
		}

		//save the employee
		employeeService.save(employee);

		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}

	@GetMapping("/showFormForUpdate")
	public String updateEmployeePage(@RequestParam("employeeId") int id , Model theModel) {

		Employee employee = employeeService.findById(id);

		theModel.addAttribute("employee",employee);

		return "Employee/employee-form";
	}


	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id) {

		employeeService.deleteById(id);

		//use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
}









