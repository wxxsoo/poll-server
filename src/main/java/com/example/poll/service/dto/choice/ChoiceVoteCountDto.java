package com.example.poll.service.dto.choice;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChoiceVoteCountDto {
    private Long choiceId;
    private String title;
    private Long voteCount;

    @Builder
    public ChoiceVoteCountDto(Long choiceId, String title, Long voteCount) {
        this.choiceId = choiceId;
        this.title = title;
        this.voteCount = voteCount;
    }
}
