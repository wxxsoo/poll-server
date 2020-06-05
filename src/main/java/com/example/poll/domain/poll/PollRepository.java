package com.example.poll.domain.poll;

import com.example.poll.domain.poll.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
