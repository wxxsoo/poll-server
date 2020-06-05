package com.example.poll.service.dto;

import com.example.poll.service.dto.choice.ChoiceResponseDto;
import com.example.poll.service.dto.poll.PollResponseDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PollAndChoiceResponseDto {
    private PollResponseDto poll;
    private List<ChoiceResponseDto> choices;

    @Builder
    public PollAndChoiceResponseDto(PollResponseDto poll, List<ChoiceResponseDto> choices) {
        this.poll = poll;
        this.choices = choices;
    }
}
