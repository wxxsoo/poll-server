package com.example.poll.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.poll.response.ApiResponseDto;
import com.example.poll.service.VoteService;
import com.example.poll.service.dto.vote.VoteCreateRequestDto;
import com.example.poll.service.dto.vote.VoteResponseDto;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

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
    
    @ApiOperation(value = "모든 투표 현황(fetchJoin 미적용)", notes = "모든 투표 현황 확인")
    @GetMapping(value = "/votes")
    public ApiResponseDto<List<VoteResponseDto>> findAll() {
        return ApiResponseDto.createOK(voteService.findAll());
    }
}
