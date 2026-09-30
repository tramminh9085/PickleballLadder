package com.pickleball.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "app_config")
@Data
public class AppConfig {
    @Id
    private Long id = 1L;
    
    private Integer maxLosses = 3;
    
    private Boolean showMaxColumn = true;
}
