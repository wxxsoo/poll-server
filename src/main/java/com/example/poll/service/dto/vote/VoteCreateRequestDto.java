package com.example.poll.service.dto.vote;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.vote.Vote;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoteCreateRequestDto {
    @ApiModelProperty(name = "pollId", dataType = "Long", value = "투표 PK", example = "1", required = true)
    private Long pollId;
    @ApiModelProperty(name = "choiceId", dataType = "Long", value = "선택지 PK", example = "2", required = true)
    private Long choiceId;
    @ApiModelProperty(name = "reasonForVoting", dataType = "String", value = "투표 이유", example = "떙겨서 투표했습니다.", required = true)
    private String reasonForVoting;

    @Builder
    public VoteCreateRequestDto(Long pollId, Long choiceId, String reasonForVoting) {
        this.pollId = pollId;
        this.choiceId = choiceId;
        this.reasonForVoting = reasonForVoting;
    }

    public Vote toEntity(Poll poll, Choice choice) {
        return Vote.builder().poll(poll).choice(choice).reasonForVoting(reasonForVoting).build();
    }
}
