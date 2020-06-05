package com.example.poll.service.dto.vote;

import com.example.poll.domain.vote.Vote;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteResponseDto {

    private String pollQuestion;
    private String choiceTitle;
    private String reasonForVoting;

    @Builder
    public VoteResponseDto(String pollQuestion, String choiceTitle, String reasonForVoting) {
        this.pollQuestion = pollQuestion;
        this.choiceTitle = choiceTitle;
        this.reasonForVoting = reasonForVoting;
    }

    public static VoteResponseDto toDto(Vote vote) {
        return VoteResponseDto.builder()
                .pollQuestion(vote.getPoll().getQuestion())
                .choiceTitle(vote.getChoice().getTitle())
                .reasonForVoting(vote.getReasonForVoting())
                .build();
    }
}
