package io.gihub.s0hye0n.aman.controller;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.dto.CreateEmployeeRequest;
import io.gihub.s0hye0n.aman.dto.EmployeeSearchRequest;
import io.gihub.s0hye0n.aman.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/api/employees")
    public ResponseEntity<List<Employee>> getEmployees(
            @ModelAttribute EmployeeSearchRequest search,
            @PageableDefault(size = 20) Pageable pageable
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee(search, pageable));
    }
}
