package org.andi.dani.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andi.dani.dtos.CreateDto;
import org.andi.dani.dtos.UserDetailDto;
import org.andi.dani.dtos.UsersDto;
import org.andi.dani.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<UsersDto> getUsers(@RequestParam(name = "page_num", required = false) Integer pageNum,
                                             @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return userService.getUsers(pageNum, pageSize);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDetailDto> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping(path = "/users", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<UserDetailDto> createUser(CreateDto createDto) {
        return userService.create(createDto);
    }
}
