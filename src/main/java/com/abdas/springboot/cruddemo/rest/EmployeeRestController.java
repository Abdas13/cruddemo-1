package com.abdas.springboot.cruddemo.rest;

import com.abdas.springboot.cruddemo.entity.Employee;
import com.abdas.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){

        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId){
        Employee employee = employeeService.findById(employeeId);

        if (employee==null){
            throw new RuntimeException("Employee id not found - "+employeeId);
        }
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        //employee.setId(0);

        Employee dbEmployee= employeeService.save(employee);
        return dbEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){

        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){

        Employee tempEmployee= employeeService.findById(employeeId);

        if (tempEmployee==null){
            throw new RuntimeException("Employye id not found - "+ employeeId );
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee id = "+employeeId;


    }
}
