package com.example.poll.service;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.domain.vote.VoteRepository;
import com.example.poll.exception.NotFoundException;
import com.example.poll.service.dto.choice.ChoiceVoteCountDto;
import com.example.poll.service.dto.poll.PollCreateRequestDto;
import com.example.poll.service.dto.poll.PollDeleteRequestDto;
import com.example.poll.service.dto.poll.PollResponseDto;
import com.example.poll.service.dto.poll.PollResultResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PollService {

    private final PollRepository pollRepository;
    private final ChoiceRepository choiceRepository;
    private final VoteRepository voteRepository;

    public void createPollWithChoices(PollCreateRequestDto create) {
        Poll poll = create.toEntity();
        pollRepository.save(poll);
        create.getChoices().forEach(dto ->{
            choiceRepository.save(dto.toEntity(poll));
        });
    }

    public List<PollResponseDto> findAll() {
        return pollRepository.findAll().stream().map(PollResponseDto::toDto).collect(Collectors.toList());
    }

    public PollResultResponseDto findOne(Long pollId) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(NotFoundException::new);
        Long totalVoteCount = voteRepository.countByPollId(pollId);

        List<ChoiceVoteCountDto> ChoiceVoteCountDtos = new ArrayList<>();
        List<Choice> choices = choiceRepository.findByPollId(pollId);
        choices.forEach(choice -> {
            ChoiceVoteCountDto dto = ChoiceVoteCountDto.builder()
                    .choiceId(choice.getId())
                    .title(choice.getTitle())
                    .voteCount(voteRepository.countByChoiceId(choice.getId()))
                    .build();
            ChoiceVoteCountDtos.add(dto);
        });
        PollResultResponseDto response = PollResultResponseDto.builder()
                .pollId(poll.getId())
                .pollMaker(poll.getPollMaker())
                .question(poll.getQuestion())
                .totalVoteCount(totalVoteCount)
                .choices(ChoiceVoteCountDtos)
                .build();

        return response;
    }

    public void delete(Long pollId, PollDeleteRequestDto request) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(NotFoundException::new);
        if(!request.getPassword().equals(poll.getPassword())) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다!");
        }
        voteRepository.deleteByPollId(pollId);
        choiceRepository.deleteByPollId(pollId);
        pollRepository.delete(poll);
    }
}
