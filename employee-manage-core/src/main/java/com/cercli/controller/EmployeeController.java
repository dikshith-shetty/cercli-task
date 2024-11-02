package com.cercli.controller;

import com.cercli.controller.request.EmployeeRequest;
import com.cercli.controller.request.EmployeeUpdateRequest;
import com.cercli.entity.Employee;
import com.cercli.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    EmployeeService es;

    @PostMapping
    @Operation(summary = "Add a new employee", description = "Create a new employee record in the system.")
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employee) {
        return es.save(employee);
    }

    @GetMapping
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees.")
    public Page<Employee> getAllEmployees(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size) {
            Pageable pageable = PageRequest.of(page, size);
        return es.getAllEmployees(pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Update details of an existing employee.")
    public Employee updateEmployee(
            @Parameter(description = "Employee having this ID to be updated")
            @PathVariable Long id, @Valid @RequestBody EmployeeUpdateRequest eup) {
        return es.update(id, eup);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their ID.")
    public Employee getAnEmployee(@PathVariable Long id) {
        return es.fetchEmployee(id);
    }

}
