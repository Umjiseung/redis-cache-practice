package com.redis.cache.practice.user.repository;

import com.redis.cache.practice.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
