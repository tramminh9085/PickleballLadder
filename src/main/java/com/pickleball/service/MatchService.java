package com.pickleball.service;

import com.pickleball.dto.DoublesMatchRequest;
import com.pickleball.model.Match;
import com.pickleball.model.PlayDay;
import com.pickleball.model.Player;
import com.pickleball.repository.MatchRepository;
import com.pickleball.repository.PlayDayRepository;
import com.pickleball.repository.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;
    private final PlayDayRepository playDayRepository;

    public MatchService(
            MatchRepository matchRepository,
            PlayerRepository playerRepository,
            PlayDayRepository playDayRepository
    ) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
        this.playDayRepository = playDayRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public Match recordDoublesMatch(DoublesMatchRequest request) {
        Map<Long, Player> players = validateAndLoadPlayers(request);
        Match match = new Match();
        match.setMatchDate(LocalDateTime.now().toString());
        match.setStatus("COMPLETED");
        match.setMatchType("DOUBLES");
        applyMatchDetails(match, request, players);
        Match savedMatch = matchRepository.saveAndFlush(match);
        recalculatePlayerStandings();
        return savedMatch;
    }

    @Transactional
    public Match updateDoublesMatch(Long id, DoublesMatchRequest request) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found"));
        Map<Long, Player> players = validateAndLoadPlayers(request);
        applyMatchDetails(match, request, players);
        Match savedMatch = matchRepository.saveAndFlush(match);
        recalculatePlayerStandings();
        return savedMatch;
    }

    @Transactional
    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Match not found");
        }
        matchRepository.deleteById(id);
        matchRepository.flush();
        recalculatePlayerStandings();
    }

    private Map<Long, Player> validateAndLoadPlayers(DoublesMatchRequest request) {
        if (request == null || request.playDayId() == null
                || request.team1Player1Id() == null || request.team1Player2Id() == null
                || request.team2Player1Id() == null || request.team2Player2Id() == null
                || request.team1Score() == null || request.team2Score() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All match fields are required");
        }

        Set<Long> playerIds = new HashSet<>(List.of(
                request.team1Player1Id(),
                request.team1Player2Id(),
                request.team2Player1Id(),
                request.team2Player2Id()
        ));
        if (playerIds.size() != 4) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A player can only appear once in a match");
        }
        if (request.team1Score() < 0 || request.team2Score() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Scores cannot be negative");
        }
        if (request.team1Score().equals(request.team2Score())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A completed match cannot end in a tie");
        }
        PlayDay playDay = playDayRepository.findById(request.playDayId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Play day not found"));
        if (!playDay.getPlayerIds().containsAll(playerIds)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "All players must belong to the selected play day"
            );
        }

        Map<Long, Player> players = playerRepository.findAllById(playerIds).stream()
                .collect(Collectors.toMap(Player::getId, Function.identity()));
        if (players.size() != 4) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "One or more players were not found");
        }
        return players;
    }

    private void applyMatchDetails(Match match, DoublesMatchRequest request, Map<Long, Player> players) {
        match.setLeagueId(1L);
        match.setPlayDayId(request.playDayId());
        match.setTeam1Player1Id(request.team1Player1Id());
        match.setTeam1Player2Id(request.team1Player2Id());
        match.setTeam2Player1Id(request.team2Player1Id());
        match.setTeam2Player2Id(request.team2Player2Id());
        match.setTeam1Player1Name(players.get(request.team1Player1Id()).getName());
        match.setTeam1Player2Name(players.get(request.team1Player2Id()).getName());
        match.setTeam2Player1Name(players.get(request.team2Player1Id()).getName());
        match.setTeam2Player2Name(players.get(request.team2Player2Id()).getName());
        match.setTeam1Score(request.team1Score());
        match.setTeam2Score(request.team2Score());
        match.setWinningTeam(request.team1Score() > request.team2Score() ? 1 : 2);
    }

    private void recalculatePlayerStandings() {
        List<Player> players = playerRepository.findAll();
        Map<Long, Standing> standings = players.stream()
                .collect(Collectors.toMap(Player::getId, player -> new Standing(player)));

        for (Match match : matchRepository.findAll()) {
            int pointDifference = match.getTeam1Score() - match.getTeam2Score();
            applyResult(standings.get(match.getTeam1Player1Id()), match.getWinningTeam() == 1, pointDifference);
            applyResult(standings.get(match.getTeam1Player2Id()), match.getWinningTeam() == 1, pointDifference);
            applyResult(standings.get(match.getTeam2Player1Id()), match.getWinningTeam() == 2, -pointDifference);
            applyResult(standings.get(match.getTeam2Player2Id()), match.getWinningTeam() == 2, -pointDifference);
        }

        List<Standing> ordered = standings.values().stream()
                .sorted(Comparator.comparingInt(Standing::wins).reversed()
                        .thenComparing(Comparator.comparingDouble(Standing::winPercentage).reversed())
                        .thenComparing(Comparator.comparingInt(Standing::pointDifference).reversed())
                        .thenComparing(standing -> standing.player().getName()))
                .toList();

        for (int index = 0; index < ordered.size(); index++) {
            Standing standing = ordered.get(index);
            standing.player().setWins(standing.wins());
            standing.player().setLosses(standing.losses());
            standing.player().setRank(index + 1);
        }
        playerRepository.saveAll(players);
    }

    private void applyResult(Standing standing, boolean won, int pointDifference) {
        if (standing == null) {
            return;
        }
        if (won) {
            standing.wins++;
        } else {
            standing.losses++;
        }
        standing.pointDifference += pointDifference;
    }

    private static final class Standing {
        private final Player player;
        private int wins;
        private int losses;
        private int pointDifference;

        private Standing(Player player) {
            this.player = player;
        }

        private Player player() {
            return player;
        }

        private int wins() {
            return wins;
        }

        private int losses() {
            return losses;
        }

        private int pointDifference() {
            return pointDifference;
        }

        private double winPercentage() {
            int games = wins + losses;
            return games == 0 ? 0 : (double) wins / games;
        }
    }
}
