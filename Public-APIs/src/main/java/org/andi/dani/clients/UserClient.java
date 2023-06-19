package org.andi.dani.clients;

import org.andi.dani.dtos.CreateDto;
import org.andi.dani.dtos.UserDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "USER-SERVICE", url = "http://localhost:8082")
public interface UserClient {
    @GetMapping("/users/{id}")
    UserDetailDto getUserDetail(@PathVariable Long id);

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    UserDetailDto createUser(@RequestBody CreateDto createDto);
}
