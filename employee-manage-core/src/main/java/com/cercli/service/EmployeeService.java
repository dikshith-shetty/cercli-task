package com.cercli.service;

import com.cercli.controller.request.EmployeeRequest;
import com.cercli.controller.request.EmployeeUpdateRequest;
import com.cercli.entity.Employee;
import com.cercli.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository eRepo;

    public Employee save(EmployeeRequest er) {
        Employee e = new Employee();
        return eRepo.save(e);
    }

    public Employee update(Long id, EmployeeUpdateRequest eur) {
        Employee employee = eRepo.findById(id).orElse(null);
        if (employee == null) {
            throw new NullPointerException();
        }
        Optional.ofNullable(eur.getEmail()).ifPresent(employee::setEmail);
        Optional.ofNullable(eur.getName()).ifPresent(employee::setName);
        Optional.ofNullable(eur.getSalary()).ifPresent(employee::setSalary);
        Optional.ofNullable(eur.getPosition()).ifPresent(employee::setPosition);
        employee.setModifiedAt(LocalDateTime.now(ZoneId.of("UTC")));
        return eRepo.save(employee);
    }

    public Employee fetchEmployee(Long id) {
        return eRepo.findById(id).orElse(null);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return eRepo.findAll(pageable);
    }
}
