package com.example.poll.domain.poll;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="polls")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "poll_id")
    private Long id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String pollMaker;
    @Column(nullable = false)
    private String password;

    @Builder
    public Poll(String question, String pollMaker, String password) {
        this.question = question;
        this.pollMaker = pollMaker;
        this.password = password;
    }
}
