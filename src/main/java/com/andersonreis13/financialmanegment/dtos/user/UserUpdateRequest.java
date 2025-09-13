package com.andersonreis13.financialmanegment.dtos.user;

import java.time.LocalDateTime;

public record UserUpdateRequest(Long id,
                                String name,
                                String email) {
}
