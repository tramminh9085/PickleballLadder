package com.pickleball.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "matches")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "league_id")
    private Long leagueId;

    @Column(name = "play_day_id", nullable = false)
    private Long playDayId;

    @Column(name = "team1_player1_id", nullable = false)
    private Long team1Player1Id;

    @Column(name = "team1_player2_id", nullable = false)
    private Long team1Player2Id;

    @Column(name = "team2_player1_id", nullable = false)
    private Long team2Player1Id;

    @Column(name = "team2_player2_id", nullable = false)
    private Long team2Player2Id;

    @Column(name = "team1_player1_name", nullable = false)
    private String team1Player1Name;

    @Column(name = "team1_player2_name", nullable = false)
    private String team1Player2Name;

    @Column(name = "team2_player1_name", nullable = false)
    private String team2Player1Name;

    @Column(name = "team2_player2_name", nullable = false)
    private String team2Player2Name;

    @Column(name = "team1_score", nullable = false)
    private Integer team1Score;

    @Column(name = "team2_score", nullable = false)
    private Integer team2Score;

    @Column(name = "winning_team", nullable = false)
    private Integer winningTeam;

    @Column(name = "match_date")
    private String matchDate;

    @Column(name = "status")
    private String status;

    @Column(name = "match_type")
    private String matchType;
}
