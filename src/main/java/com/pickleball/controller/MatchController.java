package com.pickleball.controller;

import com.pickleball.dto.DoublesMatchRequest;
import com.pickleball.model.Match;
import com.pickleball.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @PostMapping("/doubles")
    public ResponseEntity<Match> recordDoublesMatch(@RequestBody DoublesMatchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.recordDoublesMatch(request));
    }

    @PutMapping("/{id}/doubles")
    public ResponseEntity<Match> updateDoublesMatch(
            @PathVariable Long id,
            @RequestBody DoublesMatchRequest request
    ) {
        return ResponseEntity.ok(matchService.updateDoublesMatch(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
