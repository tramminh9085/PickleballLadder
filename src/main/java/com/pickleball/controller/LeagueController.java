package com.pickleball.controller;

import com.pickleball.model.League;
import com.pickleball.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leagues")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LeagueController {
    @Autowired
    private LeagueRepository leagueRepository;

    @GetMapping
    public ResponseEntity<List<League>> getAllLeagues() {
        return ResponseEntity.ok(leagueRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<League> getLeagueById(@PathVariable Long id) {
        Optional<League> league = leagueRepository.findById(id);
        return league.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<League>> getLeaguesByLocation(@PathVariable String location) {
        return ResponseEntity.ok(leagueRepository.findByLocation(location));
    }

    @PostMapping
    public ResponseEntity<League> createLeague(@RequestBody League league) {
        League created = leagueRepository.save(league);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<League> updateLeague(@PathVariable Long id, @RequestBody League leagueDetails) {
        Optional<League> league = leagueRepository.findById(id);
        if (league.isPresent()) {
            League l = league.get();
            if (leagueDetails.getName() != null) l.setName(leagueDetails.getName());
            if (leagueDetails.getLocation() != null) l.setLocation(leagueDetails.getLocation());
            if (leagueDetails.getDescription() != null) l.setDescription(leagueDetails.getDescription());
            if (leagueDetails.getStatus() != null) l.setStatus(leagueDetails.getStatus());
            League updated = leagueRepository.save(l);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Long id) {
        leagueRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
