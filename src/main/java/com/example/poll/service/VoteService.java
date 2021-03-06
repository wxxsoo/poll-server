package com.example.poll.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.poll.domain.choice.Choice;
import com.example.poll.domain.choice.ChoiceRepository;
import com.example.poll.domain.poll.Poll;
import com.example.poll.domain.poll.PollRepository;
import com.example.poll.domain.vote.Vote;
import com.example.poll.domain.vote.VoteRepository;
import com.example.poll.exception.NotFoundException;
import com.example.poll.service.dto.vote.VoteCreateRequestDto;
import com.example.poll.service.dto.vote.VoteResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteService {
	private final VoteRepository voteRepository;
	private final PollRepository pollRepository;
	private final ChoiceRepository choiceRepository;

	public Long create(VoteCreateRequestDto create) {
		Poll poll = pollRepository.findById(create.getPollId()).orElseThrow(NotFoundException::new);
		Choice choice = choiceRepository.findById(create.getChoiceId()).orElseThrow(NotFoundException::new);
		Vote vote = create.toEntity(poll, choice);
		return voteRepository.save(vote).getId();
	}

	public List<VoteResponseDto> findAll() {
//		return voteRepository.findAll().stream().map(VoteResponseDto::toDto).collect(Collectors.toList());
		return voteRepository.findAllWithFetchJoin().stream().map(VoteResponseDto::toDto).collect(Collectors.toList());
	}
}
