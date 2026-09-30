package com.pickleball.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leagues")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(name = "description")
    private String description;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "status")
    private String status; // ACTIVE, ARCHIVED, DRAFT

    @Column(name = "league_type")
    private String leagueType; // LADDER, ROUND_ROBIN, TOURNAMENT
}
