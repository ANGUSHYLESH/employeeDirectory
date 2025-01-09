package com.shylu.employeeDirectory.controller;

import com.shylu.employeeDirectory.entity.Employee;
import com.shylu.employeeDirectory.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        List<Employee> theEmployees = employeeService.findAll();
        theModel.addAttribute("employees", theEmployees);
        return "employees/listEmployees";
        }

    @GetMapping("/showFormForAdd")
    public String showEmployeeForm(Model theModel) {
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee", theEmployee);
        return "employees/showFormForAdd";
        }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theEmployeeId, Model theModel) {
        Employee theEmployee = employeeService.findById(theEmployeeId);
        theModel.addAttribute("employee", theEmployee);
        return "employees/showFormForAdd";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute ("employee") Employee theEmployee) {
        employeeService.save(theEmployee);
        return "redirect:/employees/list";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int theEmployeeId, Model theModel) {
        employeeService.deleteById(theEmployeeId);
        return "redirect:/employees/list";
    }


}
