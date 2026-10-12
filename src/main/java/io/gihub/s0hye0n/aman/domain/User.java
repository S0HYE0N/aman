package io.gihub.s0hye0n.aman.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "\"user\"")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 254)
    private String email;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne(fetch = FetchType.LAZY)   // User 조회 시 Employee는 조회하지 않도록 fetch타입을 Lazy로 설정
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

}
