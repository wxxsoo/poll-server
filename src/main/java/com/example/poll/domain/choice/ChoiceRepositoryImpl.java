package com.example.poll.domain.choice;

import com.example.poll.service.dto.choice.ChoiceVoteCountDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.poll.domain.choice.QChoice.choice;
import static com.example.poll.domain.vote.QVote.vote;

public class ChoiceRepositoryImpl implements ChoiceRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ChoiceRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Choice> findByPollId(Long pollId) {
        return queryFactory.selectFrom(choice).where(choice.poll.id.eq(pollId)).fetch();
    }

    @Override
    public void deleteByPollId(Long pollId) {
        queryFactory.delete(choice).where(choice.poll.id.eq(pollId)).execute();
    }
}
