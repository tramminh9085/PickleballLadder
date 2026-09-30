package com.pickleball.repository;

import com.pickleball.model.PlayDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayDayRepository extends JpaRepository<PlayDay, Long> {
    List<PlayDay> findAllByOrderByPlayDateDescIdDesc();
}
