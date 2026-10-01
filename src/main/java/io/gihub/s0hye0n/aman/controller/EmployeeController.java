package io.gihub.s0hye0n.aman.controller;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.dto.CreateEmployeeRequest;
import io.gihub.s0hye0n.aman.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/api/employees")
    public ResponseEntity<Long> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {

        Long createdEmployeeId = employeeService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeId);
    }

}
