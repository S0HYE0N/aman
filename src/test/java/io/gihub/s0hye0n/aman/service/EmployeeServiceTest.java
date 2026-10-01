package io.gihub.s0hye0n.aman.service;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.domain.EmployeeStatus;
import io.gihub.s0hye0n.aman.dto.CreateEmployeeRequest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @DisplayName("create employee: 새 직원 추가에 성공한다.")
    @Test
    void create() {
        // given
        CreateEmployeeRequest request = new CreateEmployeeRequest();
        request.setEmployeeNo("EMP-03");
        request.setLastName("山田");
        request.setFirstName("太郎");
        request.setLastNameKana("ヤマダ");
        request.setEmail("t.yamada@example.jp");
        request.setDepartment("設計開発1部");
        request.setHireDate(LocalDate.parse("2019-01-01"));
        request.setEmploymentStatus(EmployeeStatus.valueOf("EMPLOYED"));

        // when
        Long result_id = employeeService.create(request);

        // then
        Optional<Employee> findEmployee = employeeService.getEmployeeById(result_id);
        assertThat(findEmployee.get().getLastName()).isEqualTo(request.getLastName());

    }


}