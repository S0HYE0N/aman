package io.gihub.s0hye0n.aman.service;

import io.gihub.s0hye0n.aman.domain.Employee;
import io.gihub.s0hye0n.aman.domain.User;
import io.gihub.s0hye0n.aman.dto.CreateUserRequest;
import io.gihub.s0hye0n.aman.repository.EmployeeRepository;
import io.gihub.s0hye0n.aman.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 로그인 유저 추가
    @Transactional
    public Long createUser(CreateUserRequest request) {
        // 로그인 계정과 연결할 직원 ID 검사
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("직원을 찾을 수 없습니다."));

        // 이메일 중복 검사
        validateDuplicateUser(employee.getEmail());

        // 비밀번호 솔트 & 해시
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // user 객체 생성
        User user = User.builder()
                .email(employee.getEmail())
                .passwordHash(hashedPassword)
                .role(request.getRole())
                .employee(employee)
                .active(true)
                .build();

        return userRepository.save(user).getId();
    }

    private void validateDuplicateUser(String email) {;
        if(userRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("이메일 또는 비밀번호를 확인해주세요."));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPasswordHash())
                .disabled(!user.isActive())
                .roles(String.valueOf(user.getRole()))
                .build();
    }

}
