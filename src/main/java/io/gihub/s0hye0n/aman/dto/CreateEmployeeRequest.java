package io.gihub.s0hye0n.aman.dto;


import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.domain.EmployeeStatus;
import io.gihub.s0hye0n.aman.domain.Position;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CreateEmployeeRequest {

    private String employeeNo;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastNameKana;

    @Size(max = 50)
    private String firstNameKana;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Size(max = 254)
    private String email;

    @Size(max = 100)
    @NotBlank
    private String department;

    @NotNull(message = "입사일은 필수입니다.")
    private LocalDate hireDate;

    private LocalDate resignedDate;

    @NotNull(message = "재직상태는 필수입니다.")
    private EmployeeStatus employmentStatus;

    @NotNull(message = "직책은 필수입니다.")
    private Position position;

    public Employee toEntity() {
        return Employee.builder()
                .employeeNo(employeeNo)
                .lastName(lastName)
                .firstName(firstName)
                .lastNameKana(lastNameKana)
                .firstNameKana(firstNameKana)
                .email(email)
                .department(department)
                .hireDate(hireDate)
                .resignedDate(resignedDate)
                .employmentStatus(employmentStatus)
                .position(position)
                .build();
    }
}
