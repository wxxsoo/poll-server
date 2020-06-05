package com.example.poll.service.dto.choice;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.poll.Poll;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ChoiceCreateRequestDto {

    @ApiModelProperty(name = "title", dataType = "String", value = "질문명", example = "치킨", required = true)
    private String title;

    @Builder
    public ChoiceCreateRequestDto(String title) {
        this.title = title;
    }

    public Choice toEntity(Poll poll) {
        return Choice.builder().title(title).poll(poll).build();
    }
}
