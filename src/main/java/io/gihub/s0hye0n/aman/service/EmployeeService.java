package io.gihub.s0hye0n.aman.service;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.dto.CreateEmployeeRequest;
import io.gihub.s0hye0n.aman.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // user create
    @Transactional
    public Long create(CreateEmployeeRequest request) {

        // Request DTO -> Entity 로 변환
        Employee employee = request.toEntity();

        // 중복 검증
        validateDuplicateEmployee(employee);

        // 저장
        employeeRepository.save(employee);
        return employee.getId();
    }

    private void validateDuplicateEmployee(Employee employee) {
        if(employee.getEmployeeNo() != null && employeeRepository.existsByEmployeeNo(employee.getEmployeeNo())) {
            throw new IllegalStateException("이미 존재하는 사원번호입니다.");
        }


        if(employeeRepository.existsByEmail(employee.getEmail()))
            throw new IllegalStateException("이미 존재하는 이메일입니다.");

    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
}
