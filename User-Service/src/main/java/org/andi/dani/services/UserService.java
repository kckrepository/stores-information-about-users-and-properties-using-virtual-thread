package org.andi.dani.services;

import lombok.AllArgsConstructor;
import org.andi.dani.dtos.CreateDto;
import org.andi.dani.dtos.UserDetailDto;
import org.andi.dani.dtos.UserDto;
import org.andi.dani.dtos.UsersDto;
import org.andi.dani.entities.User;
import org.andi.dani.repositories.UserRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public ResponseEntity<UsersDto> getUsers(Integer pageNum, Integer pageSize) {
        if (Objects.isNull(pageNum)) {
            pageNum = 0;
        }

        if (pageNum > 0) {
            pageNum = pageNum - 1;
        }

        if (Objects.isNull(pageSize) || pageSize == 0) {
            pageSize = 10;
        }

        Pageable pageable =
                PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());

        var list = userRepo.findAll(pageable).stream().map(user -> {
            var userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setUpdateAt(user.getUpdatedAt());

            return userDto;
        }).collect(Collectors.toList());

        var usersDto = new UsersDto();
        usersDto.setUsers(list);
        usersDto.setResult(true);

        return new ResponseEntity<UsersDto>(usersDto, HttpStatus.OK);
    }

    public ResponseEntity<UserDetailDto> getUser(Long id) {
        var user = userRepo.findById(id);
        if (user.isPresent()) {
            var userDto = new UserDto();
            userDto.setId(user.get().getId());
            userDto.setName(user.get().getName());
            userDto.setCreatedAt(user.get().getCreatedAt());
            userDto.setUpdateAt(user.get().getUpdatedAt());

            var userDetailDto = new UserDetailDto();
            userDetailDto.setResult(true);
            userDetailDto.setUserDto(userDto);
            return new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.OK);
        }
        else {
            var userDetailDto = new UserDetailDto();
            userDetailDto.setResult(false);
            return new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserDetailDto> create(CreateDto createDto) {
        if (Objects.isNull(createDto.getName()) || createDto.getName().isBlank()) {
            var userDetailDto = new UserDetailDto();
            userDetailDto.setResult(false);
            return new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setName(createDto.getName());
        var resultUser = userRepo.save(newUser);

        var userDto = new UserDto();
        userDto.setId(resultUser.getId());
        userDto.setName(resultUser.getName());
        userDto.setCreatedAt(resultUser.getCreatedAt());
        userDto.setUpdateAt(resultUser.getUpdatedAt());

        var userDetailDto = new UserDetailDto();
        userDetailDto.setUserDto(userDto);
        userDetailDto.setResult(true);

        return new ResponseEntity<UserDetailDto>(userDetailDto, HttpStatus.CREATED);
    }
}