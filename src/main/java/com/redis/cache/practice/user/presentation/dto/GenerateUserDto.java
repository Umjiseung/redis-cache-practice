package com.redis.cache.practice.user.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GenerateUserDto {
    private Long id;
    private String name;
    private String email;
}
