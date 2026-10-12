package io.gihub.s0hye0n.aman.dto;

import io.gihub.s0hye0n.aman.domain.UserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @NotNull(message = "로그인 유저와 연결할 직원 선택은 필수입니다.")
    @Positive(message = "로그인 유저와 연결할 직원을 확인해주세요.")
    private Long employeeId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자리로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.{8,16}$)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9\\s])[^\\s\\p{Cntrl}]+$",
            message = "비밀번호는 8~16자이며 영문 대소문자, 숫자, 특수문자를 각각 포함해야 합니다."
    )
    private String password;

    @NotNull(message = "권한은 필수입니다.")
    private UserRole role;

}
