package com.example.poll.service.dto.poll;

import com.example.poll.domain.poll.Poll;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollResponseDto {
    private Long pollId;
    private String question;
    private String pollMaker;

    @Builder
    public PollResponseDto(Long pollId, String question, String pollMaker) {
        this.pollId = pollId;
        this.pollMaker = pollMaker;
        this.question = question;
    }

    public static PollResponseDto toDto(Poll poll) {
        return PollResponseDto.builder()
                .question(poll.getQuestion())
                .pollId(poll.getId())
                .pollMaker(poll.getPollMaker())
                .build();
    }
}
