package com.example.poll.service.dto.choice;

import com.example.poll.domain.choice.Choice;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChoiceResponseDto {
    private Long choiceId;
    private String title;

    @Builder
    public ChoiceResponseDto(Long choiceId, String title) {
        this.choiceId = choiceId;
        this.title = title;
    }

    public static ChoiceResponseDto toDto(Choice choice) {
        return ChoiceResponseDto.builder()
                .choiceId(choice.getId())
                .title(choice.getTitle())
                .build();
    }

}
