package io.gihub.s0hye0n.aman.controller;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.dto.CreateEmployeeRequest;
import io.gihub.s0hye0n.aman.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // TODO: 이름, 이메일, 부서, 재직상태로 검색&필터링 기능 추가
    // page, size, sort를 입력
    // default : page = 0, size = 10
    // request 방법 : employees?page=1&size=10&sort=firstName,desc
    @GetMapping("/api/employees")
    public ResponseEntity<Page<Employee>> getEmployees(
            @PageableDefault(sort = "id") Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee(pageable));
    }
}
