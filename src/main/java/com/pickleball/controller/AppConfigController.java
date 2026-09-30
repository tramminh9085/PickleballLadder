package com.pickleball.controller;

import com.pickleball.model.AppConfig;
import com.pickleball.repository.AppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AppConfigController {
    
    @Autowired
    private AppConfigRepository appConfigRepository;

    @GetMapping
    public ResponseEntity<AppConfig> getConfig() {
        return ResponseEntity.ok(appConfigRepository.findById(1L).orElse(new AppConfig()));
    }

    @PutMapping
    public ResponseEntity<AppConfig> updateConfig(@RequestBody AppConfig config) {
        config.setId(1L);
        return ResponseEntity.ok(appConfigRepository.save(config));
    }
}
