package org.andi.dani.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UsersDto {
    private boolean result;
    private List<UserDto> users;
}
