package io.gihub.s0hye0n.aman.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSearchRequest {

    private String name;
    private String email;
    private String department;
    private String employmentStatus;

}
