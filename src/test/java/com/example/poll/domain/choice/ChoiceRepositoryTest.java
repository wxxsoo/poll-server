package com.example.poll.domain.choice;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Profile(value = "test")
public class ChoiceRepositoryTest {

    @Autowired
    PollRepository pollRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    
    @Test
    public void Choice_저장하기() throws Exception {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        pollRepository.save(poll);
        Choice choice1 = Choice.builder().title("축구").poll(poll).build();
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        Choice choice3 = Choice.builder().title("농구").poll(poll).build();

        //when
        Choice result1 = choiceRepository.save(choice1);
        Choice result2 = choiceRepository.save(choice2);
        Choice result3 = choiceRepository.save(choice3);

        assertThat(result1.getTitle()).isEqualTo(choice1.getTitle());
        assertThat(result1.getPoll().getId()).isEqualTo(poll.getId());
        assertThat(result2.getTitle()).isEqualTo(choice2.getTitle());
        assertThat(result2.getPoll().getId()).isEqualTo(poll.getId());
        assertThat(result3.getTitle()).isEqualTo(choice3.getTitle());
        assertThat(result3.getPoll().getId()).isEqualTo(poll.getId());
    }

    @Test
    public void findByPollId_test() {

        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        Long pollId = pollRepository.save(poll).getId();
        Choice choice1 = Choice.builder().title("축구").poll(poll).build();
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        Choice choice3 = Choice.builder().title("농구").poll(poll).build();
        Choice result1 = choiceRepository.save(choice1);
        Choice result2 = choiceRepository.save(choice2);
        Choice result3 = choiceRepository.save(choice3);

        //when
        List<Choice> resultList = choiceRepository.findByPollId(pollId);

        //then
        assertThat(resultList.size()).isEqualTo(3);
    }

    @Test
    public void deleteByPollId_test() {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        Long pollId = pollRepository.save(poll).getId();
        Choice choice1 = Choice.builder().title("축구").poll(poll).build();
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        Choice choice3 = Choice.builder().title("농구").poll(poll).build();
        Choice result1 = choiceRepository.save(choice1);
        Choice result2 = choiceRepository.save(choice2);
        Choice result3 = choiceRepository.save(choice3);
        //when
        assertThat(3L).isEqualTo(choiceRepository.count());
        choiceRepository.deleteByPollId(pollId);
        //then
        assertThat(0L).isEqualTo(choiceRepository.count());
    }
}
