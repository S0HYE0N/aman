package io.gihub.s0hye0n.aman.repository;

import io.gihub.s0hye0n.aman.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmployeeNo(String employeeNo);

    boolean existsByEmail(String email);
}
