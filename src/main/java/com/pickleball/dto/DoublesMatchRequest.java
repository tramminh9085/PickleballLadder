package com.pickleball.dto;

public record DoublesMatchRequest(
        Long playDayId,
        Long team1Player1Id,
        Long team1Player2Id,
        Long team2Player1Id,
        Long team2Player2Id,
        Integer team1Score,
        Integer team2Score
) {
}
