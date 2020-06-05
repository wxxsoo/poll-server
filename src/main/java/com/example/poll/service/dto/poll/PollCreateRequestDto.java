package com.example.poll.service.dto.poll;

import com.example.poll.domain.poll.Poll;
import com.example.poll.service.dto.choice.ChoiceCreateRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollCreateRequestDto {
    @ApiModelProperty(name = "question", dataType = "String", value = "투표명", example = "가장 좋아하는 음식은?", required = true)
    private String question;
    @ApiModelProperty(name = "pollMaker", dataType = "String", value = "투표 작성자", example = "lee", required = true)
    private String pollMaker;
    @ApiModelProperty(name = "password", dataType = "String", value = "비밀번호", example = "1234", required = true)
    private String password;
    private List<ChoiceCreateRequestDto> choices = new ArrayList<>();

    @Builder
    public PollCreateRequestDto(String question, String pollMaker, String password, List<ChoiceCreateRequestDto> choices) {
        this.question = question;
        this.pollMaker = pollMaker;
        this.password = password;
        this.choices = choices;
    }

    public Poll toEntity() {
        return Poll.builder().question(question).pollMaker(pollMaker).password(password).build();
    }
}
