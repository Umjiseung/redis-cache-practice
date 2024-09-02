package com.redis.cache.practice.user.presentation;

import com.redis.cache.practice.user.presentation.dto.GenerateUserDto;
import com.redis.cache.practice.user.presentation.dto.GetUserDto;
import com.redis.cache.practice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> generateUser(@RequestBody GenerateUserDto generateUserDto) {
        userService.generateUser(generateUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<GetUserDto>> getsUser() {
        List<GetUserDto> response = userService.getsUser();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
