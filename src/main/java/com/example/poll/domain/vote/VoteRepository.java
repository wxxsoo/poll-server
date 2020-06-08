package com.example.poll.domain.vote;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
	
//	@Query("select v from Votes v join fetch v.poll p join fetch v.chocie c")
//	public List<Vote> findAllWithFetchJoin();
}
