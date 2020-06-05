package com.example.poll.domain.vote;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.poll.domain.vote.QVote.vote;
import static com.example.poll.domain.choice.QChoice.choice;
import static com.example.poll.domain.poll.QPoll.poll;

public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public VoteRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Vote> findByChoiceIdWithFetchJoin(Long choiceId) {
        return queryFactory.selectFrom(vote)
                .join(vote.choice, choice).fetchJoin()
                .where(vote.choice.id.eq(choiceId))
                .fetch();
    }

    @Override
    public List<Vote> findByPollIdWithFetchJoin(Long pollId) {
        return queryFactory.selectFrom(vote)
                .join(vote.choice, choice).fetchJoin()
                .join(vote.poll, poll).fetchJoin()
                .where(vote.poll.id.eq(pollId))
                .fetch();
    }

    @Override
    public Long countByChoiceId(Long choiceId) {
        return queryFactory.selectFrom(vote)
                .where(vote.choice.id.eq(choiceId)).fetchCount();
    }

    @Override
    public Long countByPollId(Long pollId) {
        return queryFactory.selectFrom(vote)
                .where(vote.poll.id.eq(pollId)).fetchCount();
    }

    @Override
    public void deleteByPollId(Long pollId) {
        queryFactory.delete(vote).where(vote.poll.id.eq(pollId)).execute();
    }
}
