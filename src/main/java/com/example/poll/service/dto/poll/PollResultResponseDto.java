package com.example.poll.service.dto.poll;

import com.example.poll.service.dto.choice.ChoiceVoteCountDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollResultResponseDto {
    private Long pollId;
    private String question;
    private String pollMaker;
    private Long totalVoteCount;
    private List<ChoiceVoteCountDto> choices = new ArrayList<>();

    @Builder
    public PollResultResponseDto(Long pollId, String question, String pollMaker, Long totalVoteCount, List<ChoiceVoteCountDto> choices) {
        this.pollId = pollId;
        this.question = question;
        this.pollMaker = pollMaker;
        this.totalVoteCount = totalVoteCount;
        this.choices = choices;
    }
}
