package com.example.poll.config;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.domain.vote.Vote;
import com.example.poll.domain.vote.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Profile(value = "local-dev")
public class InitDB {

    @Autowired
    PollRepository pollRepository;
    @Autowired
    ChoiceRepository choiceRepository;
    @Autowired
    VoteRepository voteRepository;

    @PostConstruct
    public void init() {
        Poll poll = Poll.builder().question("가장 좋아하는 운동은?").pollMaker("lee").password("123").build();
        pollRepository.save(poll);
        Choice choice1 = choiceRepository.save(Choice.builder().title("축구").poll(poll).build());
        Choice choice2 = choiceRepository.save(Choice.builder().title("야구").poll(poll).build());
        Choice choice3 = choiceRepository.save(Choice.builder().title("농구").poll(poll).build());

        for(int i=0; i<20; i++) {
            Vote vote = Vote.builder().poll(poll).choice(choice1).reasonForVoting("축구가 재밌어요"+i).build();
            voteRepository.save(vote);
        }

        for(int i=0; i<10; i++) {
            Vote vote = Vote.builder().poll(poll).choice(choice2).reasonForVoting("야구가 재밌어요"+i).build();
            voteRepository.save(vote);
        }

        for(int i=0; i<5; i++) {
            Vote vote = Vote.builder().poll(poll).choice(choice3).reasonForVoting("농구가 하고싶어요"+i).build();
            voteRepository.save(vote);
        }
    }
}
