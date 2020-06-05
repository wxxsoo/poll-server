package com.example.poll.service;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.domain.vote.Vote;
import com.example.poll.domain.vote.VoteRepository;
import com.example.poll.service.dto.*;
import com.example.poll.service.dto.choice.ChoiceCreateRequestDto;
import com.example.poll.service.dto.poll.PollCreateRequestDto;
import com.example.poll.service.dto.poll.PollDeleteRequestDto;
import com.example.poll.service.dto.poll.PollResponseDto;
import com.example.poll.service.dto.poll.PollResultResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Commit;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Profile(value = "test")
public class PollServiceTest {

    @Autowired
    PollRepository pollRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    VoteRepository voteRepository;
    @Autowired
    PollService pollService;

    @Test
    public void createPollWithChoices_test() {
        // given
        List<ChoiceCreateRequestDto> choices = new ArrayList<>();
        ChoiceCreateRequestDto c1 = ChoiceCreateRequestDto.builder().title("치킨").build();
        ChoiceCreateRequestDto c2 = ChoiceCreateRequestDto.builder().title("피자").build();
        ChoiceCreateRequestDto c3 = ChoiceCreateRequestDto.builder().title("햄버거").build();

        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        PollCreateRequestDto pollCreateRequestDto = PollCreateRequestDto
                .builder()
                .question("가장 좋아하는 운동은?")
                .pollMaker("lee")
                .password("123")
                .choices(choices)
                .build();
        // when
        pollService.createPollWithChoices(pollCreateRequestDto);
        //then
        assertThat(pollRepository.count()).isEqualTo(1L);
        assertThat(choiceRepository.count()).isEqualTo(3L);
    }

    @Test
    public void findAll_test() {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 음식은?").pollMaker("lee").password("123").build();
        Poll poll2 = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("321").build();
        pollRepository.save(poll);
        pollRepository.save(poll2);
        //when
        List<PollResponseDto> resultList = pollService.findAll();
        //then
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    public void findOne_test() {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        Long pollId = pollRepository.save(poll).getId();
        List<Choice> choiceList = new ArrayList<>();
        Choice choice1 = choiceRepository.save(Choice.builder().title("축구").poll(poll).build());
        Choice choice2 = choiceRepository.save(Choice.builder().title("야구").poll(poll).build());
        Choice choice3 = choiceRepository.save(Choice.builder().title("농구").poll(poll).build());
        choiceList.add(choice1);
        choiceList.add(choice2);
        choiceList.add(choice3);

        Vote vote1 = Vote.builder().poll(poll).choice(choice1).reasonForVoting("like it1!").build();
        Vote vote2 = Vote.builder().poll(poll).choice(choice1).reasonForVoting("like it2!").build();
        Vote vote3 = Vote.builder().poll(poll).choice(choice1).reasonForVoting("like it3!").build();

        voteRepository.save(vote1);
        voteRepository.save(vote2);
        voteRepository.save(vote3);

        //when
        PollResultResponseDto result = pollService.findOne(pollId);

        //then
        assertThat(result.getPollId()).isEqualTo(pollId);
        assertThat(result.getQuestion()).isEqualTo(poll.getQuestion());
        assertThat(result.getPollMaker()).isEqualTo(poll.getPollMaker());
        for(int i=0; i<result.getChoices().size(); i++) {
            assertThat(result.getChoices().get(i).getChoiceId()).isEqualTo(choiceList.get(i).getId());
            assertThat(result.getChoices().get(i).getTitle()).isEqualTo(choiceList.get(i).getTitle());
        }
        assertThat(result.getChoices().get(0).getVoteCount()).isEqualTo(3L);
        assertThat(result.getChoices().get(1).getVoteCount()).isEqualTo(0L);
        assertThat(result.getChoices().get(2).getVoteCount()).isEqualTo(0L);
    }

    @Test
    public void delete_test() {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        Long pollId = pollRepository.save(poll).getId();
        List<Choice> choiceList = new ArrayList<>();
        Choice choice1 = choiceRepository.save(Choice.builder().title("축구").poll(poll).build());
        Choice choice2 = choiceRepository.save(Choice.builder().title("야구").poll(poll).build());
        Choice choice3 = choiceRepository.save(Choice.builder().title("농구").poll(poll).build());
        choiceList.add(choice1);
        choiceList.add(choice2);
        choiceList.add(choice3);


        PollDeleteRequestDto delete = new PollDeleteRequestDto(poll.getPassword());
        //when
        assertThat(choiceRepository.count()).isEqualTo(3L);
        pollService.delete(pollId,delete);
        //then
        assertThat(choiceRepository.count()).isEqualTo(0L);
    }
}
