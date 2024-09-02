package com.redis.cache.practice.user.service;

import com.redis.cache.practice.user.User;
import com.redis.cache.practice.user.presentation.dto.GenerateUserDto;
import com.redis.cache.practice.user.presentation.dto.GetUserDto;
import com.redis.cache.practice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    @Cacheable(key = "#dto.id", value = "User", cacheManager = "userCacheManager")
    public void generateUser(GenerateUserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Cacheable(value = "UserList", cacheManager = "userCacheManager")
    public List<GetUserDto> getsUser() {
        return userRepository.findAll().stream()
                .map(user -> GetUserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "User", key = "#id", cacheManager = "userCacheManager")
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow();

        userRepository.delete(user);
    }
}
