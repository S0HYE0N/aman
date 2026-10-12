package io.gihub.s0hye0n.aman.dto;

import io.gihub.s0hye0n.aman.domain.UserRole;

public record MeResponse(Long id, String email, UserRole role) {
}
