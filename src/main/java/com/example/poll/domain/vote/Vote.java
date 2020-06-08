package com.example.poll.domain.vote;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.poll.Poll;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id", nullable = false)
    private Choice choice;

    private String reasonForVoting;

    @Builder
    public Vote(Poll poll, Choice choice, String reasonForVoting) {
        this.poll = poll;
        this.choice = choice;
        this.reasonForVoting = reasonForVoting;
    }
}
