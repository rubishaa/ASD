package com.track.expensetrackerapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
}
