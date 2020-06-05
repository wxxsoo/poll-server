package com.example.poll.domain.choice;

import com.example.poll.domain.poll.Poll;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "choices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    @Builder
    public Choice(String title, Poll poll) {
        this.title = title;
        this.poll = poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
