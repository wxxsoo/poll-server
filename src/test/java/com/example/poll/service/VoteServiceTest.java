package com.example.poll.service;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.domain.vote.Vote;
import com.example.poll.domain.vote.VoteRepository;
import com.example.poll.service.dto.vote.VoteCreateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Profile(value = "test")
public class VoteServiceTest {

    @Autowired
    PollRepository pollRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    VoteService voteService;

    @Test
    public void createPollWithChoices_test() {
        // given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        pollRepository.save(poll);
        Choice choice1 = Choice.builder().title("축구").poll(poll).build();
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        Choice choice3 = Choice.builder().title("농구").poll(poll).build();
        choiceRepository.save(choice1);
        Choice choice = choiceRepository.save(choice2);
        choiceRepository.save(choice3);

        VoteCreateRequestDto create = VoteCreateRequestDto.builder().pollId(poll.getId())
                .choiceId(choice.getId()).reasonForVoting("재밌어서요")
                .build();
        // when
        Long voteId = voteService.create(create);
        // then
        Vote result = voteRepository.findById(voteId).get();

        assertThat(result.getPoll().getId()).isEqualTo(create.getPollId());
        assertThat(result.getChoice().getId()).isEqualTo(create.getChoiceId());
        assertThat(result.getReasonForVoting()).isEqualTo(create.getReasonForVoting());
    }
}
