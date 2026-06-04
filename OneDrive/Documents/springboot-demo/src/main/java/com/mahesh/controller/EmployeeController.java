package com.mahesh.controller;

import com.mahesh.dto.EmployeeDTO;
import com.mahesh.model.Employee;
import com.mahesh.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@Tag(
        name = "Employee Management",
        description = "Employee CRUD APIs with Role Based Security"
)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // ADMIN ONLY
    @Operation(summary = "Create Employee")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Employee saveEmployee(
            @Valid @RequestBody Employee employee) {

        return employeeService.saveEmployee(employee);
    }

    // ADMIN + USER
    @Operation(summary = "Get All Employees")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    // ADMIN + USER
    @Operation(summary = "Get Employee By Id")
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public EmployeeDTO getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    // ADMIN ONLY
    @Operation(summary = "Update Employee")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {

        return employeeService.updateEmployee(
                id,
                employee);
    }

    // ADMIN ONLY
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEmployee(
            @PathVariable Long id) {

        return employeeService.deleteEmployee(id);
    }
}
