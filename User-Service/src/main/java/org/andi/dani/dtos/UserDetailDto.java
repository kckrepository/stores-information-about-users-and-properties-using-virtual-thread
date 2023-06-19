package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDetailDto {
    @JsonProperty("user")
    private UserDto userDto;
    private boolean result;
}
