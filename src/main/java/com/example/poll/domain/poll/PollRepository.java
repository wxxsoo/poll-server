package com.example.poll.domain.poll;

import com.example.poll.domain.poll.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
}
