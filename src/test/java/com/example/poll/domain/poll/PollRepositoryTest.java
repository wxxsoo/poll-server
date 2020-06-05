package com.example.poll.domain.poll;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
//@Profile(value = "test")
public class PollRepositoryTest {

    @Autowired
    PollRepository pollRepository;

    @Test
    public void Poll을_저장하기() throws Exception{
        //given
        Poll poll = Poll.builder().question("가장 좋아하는 음식은?").pollMaker("lee").password("123").build();
        //when
        Long id = pollRepository.save(poll).getId();
        //then
        Poll result = pollRepository.findById(id).get();
//        assertThat(pollRepository.count()).isEqualTo(2L);
        assertThat(result.getQuestion()).isEqualTo(poll.getQuestion());
        assertThat(result.getPollMaker()).isEqualTo(poll.getPollMaker());
        assertThat(result.getPassword()).isEqualTo(poll.getPassword());
    }


}
