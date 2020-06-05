package com.example.poll.service.dto.poll;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollDeleteRequestDto {
    @ApiModelProperty(name = "password", dataType = "String", value = "비밀번호", example = "1234", required = true)
    private String password;

    public PollDeleteRequestDto(String password) {
        this.password = password;
    }
}
