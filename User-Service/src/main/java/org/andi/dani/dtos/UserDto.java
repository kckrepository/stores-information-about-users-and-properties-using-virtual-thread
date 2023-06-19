package org.andi.dani.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private long id;
    private String name;
    @JsonProperty("created_at")
    private Date CreatedAt;
    @JsonProperty("updated_at")
    private Date UpdateAt;
}
