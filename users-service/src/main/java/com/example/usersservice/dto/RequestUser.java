package com.example.usersservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {
    @NotNull(message = "이메일은 null이 될 수 없음")
    @Size(min = 2, message = "최소 2자 이상.")
    private String email;

    @NotNull(message = "비밀번호는 null이 될 수 없음")
    @Size(min = 8, message = "최소 8자 이상, 16자 이하")
    private String pwd;

    @NotNull(message = "이름은 null이 될 수 없음")
    @Size(min = 2, message = "최소 2자 이상")
    private String name;
}
