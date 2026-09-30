package com.pickleball.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private Long id;
    private String name;
    private String email;
    private Double skillLevel;
    private Integer wins;
    private Integer losses;
    private Double rating;
    private Integer rank;
    private String location;
    private String phoneNumber;
    private String createdAt;
}
