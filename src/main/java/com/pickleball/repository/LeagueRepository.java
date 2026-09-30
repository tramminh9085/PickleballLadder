package com.pickleball.repository;

import com.pickleball.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    List<League> findByStatus(String status);
    List<League> findByLocation(String location);
}
