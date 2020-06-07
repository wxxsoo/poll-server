package com.example.poll.domain.vote;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.service.dto.choice.ChoiceVoteCountDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Profile(value = "test")
public class VoteRepositoryTest {
    @Autowired
    PollRepository pollRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    VoteRepository voteRepository;

    @Test
    public void Vote_저장하기() throws Exception {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        pollRepository.save(poll);
        Choice choice = Choice.builder().title("축구").poll(poll).build();
        choiceRepository.save(choice);
        //when
        Vote vote = Vote.builder().choice(choice).poll(poll).reasonForVoting("그냥").build();
        Vote result = voteRepository.save(vote);
        //then
        assertThat(vote.getReasonForVoting()).isEqualTo(result.getReasonForVoting());
        assertThat(vote.getPoll().getId()).isEqualTo(poll.getId());
        assertThat(vote.getChoice().getId()).isEqualTo(choice.getId());
    }

    @Test
    public void findByChoiceIdWithFetchJoin_method_test() throws Exception {
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        pollRepository.save(poll);
        Choice choice = Choice.builder().title("축구").poll(poll).build();
        choiceRepository.save(choice);
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        choiceRepository.save(choice2);

        Vote vote1 = Vote.builder().choice(choice).poll(poll).reasonForVoting("축구가 좋아요").build();
        voteRepository.save(vote1);
        Vote vote2 = Vote.builder().choice(choice).poll(poll).reasonForVoting("축구는 재밌어요").build();
        voteRepository.save(vote2);
        Vote vote3 = Vote.builder().choice(choice2).poll(poll).reasonForVoting("야구가 좋아요").build();
        voteRepository.save(vote3);

        //when
        List<Vote> votesForFootball = voteRepository.findByChoiceIdWithFetchJoin(choice.getId());
        //then
        assertThat(votesForFootball.size()).isEqualTo(2);
    }


    @Autowired
    EntityManager em;

    @Test
    public void Poll_저장하기() {
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        em.persist(poll);
        em.flush();
    }

    @Test
    public void Poll과_Choice를_저장하기() {
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        Choice choice = Choice.builder().title("축구").build();
        choice.setPoll(poll);
        Choice choice2 = Choice.builder().title("야구").poll(poll).build();
        choice2.setPoll(poll);
        em.persist(poll);
        em.persist(choice);
        em.persist(choice2);
        em.flush();
    }
}
