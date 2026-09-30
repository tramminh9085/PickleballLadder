package com.pickleball.repository;

import com.pickleball.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByLeagueId(Long leagueId);
    List<Match> findAllByOrderByIdDesc();
}
