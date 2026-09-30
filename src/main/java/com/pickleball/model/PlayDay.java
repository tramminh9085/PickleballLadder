package com.pickleball.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "play_days")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "play_date", nullable = false)
    private String playDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "play_day_players", joinColumns = @JoinColumn(name = "play_day_id"))
    @Column(name = "player_id", nullable = false)
    private Set<Long> playerIds = new HashSet<>();
}
