package com.example.poll.controller;

import com.example.poll.response.ApiResponseDto;
import com.example.poll.service.PollService;
import com.example.poll.service.dto.*;
import com.example.poll.service.dto.poll.PollCreateRequestDto;
import com.example.poll.service.dto.poll.PollDeleteRequestDto;
import com.example.poll.service.dto.poll.PollResponseDto;
import com.example.poll.service.dto.poll.PollResultResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PollController {
    private final PollService pollService;

    @ApiOperation(value = "투표 생성", notes = "새로운 투표를 생성한다.")
    @PostMapping(value = "/polls")
    public ApiResponseDto<String> create(@Validated @RequestBody PollCreateRequestDto create) {
        pollService.createPollWithChoices(create);
        return ApiResponseDto.DEFAULT_OK;
    }

    @ApiOperation(value = "모든 투표 조회", notes = "모든 투표를 조회한다.")
    @GetMapping(value = "/polls")
    public ApiResponseDto<List<PollResponseDto>> findAll() {
        return ApiResponseDto.createOK(pollService.findAll());
    }

    @ApiOperation(value = "투표 단건 조회", notes = "투표 단건 조회한다.")
    @GetMapping(value = "/polls/{pollId}")
    public ApiResponseDto<PollResultResponseDto> findOne(
            @ApiParam(name = "pollId", type = "Long", value = "투표 PK", example = "1", required = true)
             @PathVariable("pollId") Long pollId) {
        return ApiResponseDto.createOK(pollService.findOne(pollId));
    }

//    @ApiOperation(value = "투표 단건 조회", notes = "투표 단건 조회한다.")
//    @GetMapping(value = "/polls/{pollId}/result")
//    public ApiResponseDto<PollResultResponseDto> findResult(
//            @ApiParam(name = "pollId", type = "Long", value = "투표 PK", example = "1", required = true)
//            @PathVariable("pollId") Long pollId) {
//        return ApiResponseDto.createOK(pollService.findReuslt(pollId));
//    }

    @ApiOperation(value = "투표 삭제", notes = "투표 한개를 삭제한다.")
    @DeleteMapping(value = "/polls/{pollId}")
    public ApiResponseDto<String> delete(
            @ApiParam(name = "pollId", type = "Long", value = "투표 PK", example = "1", required = true) @PathVariable("pollId") Long pollId,
            @Validated @RequestBody PollDeleteRequestDto delete) {
        pollService.delete(pollId,delete);
        return ApiResponseDto.DEFAULT_OK;
    }
}
