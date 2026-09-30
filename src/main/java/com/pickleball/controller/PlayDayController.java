package com.pickleball.controller;

import com.pickleball.model.PlayDay;
import com.pickleball.repository.PlayDayRepository;
import com.pickleball.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/play-days")
public class PlayDayController {
    private final PlayDayRepository playDayRepository;
    private final PlayerRepository playerRepository;

    public PlayDayController(PlayDayRepository playDayRepository, PlayerRepository playerRepository) {
        this.playDayRepository = playDayRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping
    public ResponseEntity<List<PlayDay>> getAllPlayDays() {
        return ResponseEntity.ok(playDayRepository.findAllByOrderByPlayDateDescIdDesc());
    }

    @PostMapping
    public ResponseEntity<PlayDay> createPlayDay(@RequestBody PlayDay playDay) {
        validate(playDay);
        playDay.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(playDayRepository.save(playDay));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayDay> updatePlayDay(@PathVariable Long id, @RequestBody PlayDay details) {
        validate(details);
        PlayDay playDay = playDayRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Play day not found"));
        playDay.setName(details.getName());
        playDay.setPlayDate(details.getPlayDate());
        playDay.setPlayerIds(new HashSet<>(details.getPlayerIds()));
        return ResponseEntity.ok(playDayRepository.save(playDay));
    }

    private void validate(PlayDay playDay) {
        if (playDay == null || playDay.getName() == null || playDay.getName().isBlank()
                || playDay.getPlayDate() == null || playDay.getPlayDate().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name and play date are required");
        }
        if (playDay.getPlayerIds() == null) {
            playDay.setPlayerIds(new HashSet<>());
        }
        long existingPlayers = playerRepository.findAllById(playDay.getPlayerIds()).size();
        if (existingPlayers != playDay.getPlayerIds().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "One or more players do not exist");
        }
    }
}
