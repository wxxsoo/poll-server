package com.example.poll.controller;

import com.example.poll.response.ApiResponseDto;
import com.example.poll.service.VoteService;
import com.example.poll.service.dto.vote.VoteCreateRequestDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @ApiOperation(value = "투표하기", notes = "투표한다.")
    @PostMapping(value = "/votes")
    public ApiResponseDto<String> create(@Validated @RequestBody VoteCreateRequestDto create) {
        voteService.create(create);
        return ApiResponseDto.DEFAULT_OK;
    }
}
