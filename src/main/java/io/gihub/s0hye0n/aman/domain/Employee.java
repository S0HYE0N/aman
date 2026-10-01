package io.gihub.s0hye0n.aman.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Employee extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeNo;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastNameKana;

    @Column(length = 50)
    private String firstNameKana;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false, length = 100)
    private String department;

    @Column(nullable = false)
    private LocalDate hireDate;

    private LocalDate resignedDate;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employmentStatus;

    @Enumerated(EnumType.STRING)
    private Position position;

}