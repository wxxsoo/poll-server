package com.example.poll.domain.vote;

import java.util.List;

public interface VoteRepositoryCustom {
    List<Vote> findByChoiceIdWithFetchJoin(Long choiceId);
    List<Vote> findByPollIdWithFetchJoin(Long pollId);
    Long countByChoiceId(Long choiceId);
    Long countByPollId(Long pollId);
    void deleteByPollId(Long pollId);
}
