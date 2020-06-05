package com.example.poll.domain.choice;

import com.example.poll.service.dto.choice.ChoiceVoteCountDto;

import java.util.List;

public interface ChoiceRepositoryCustom {
    List<Choice> findByPollId(Long pollId);
    void deleteByPollId(Long pollId);
}
