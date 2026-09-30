package com.pickleball.service;

import com.pickleball.dto.PlayerDTO;
import com.pickleball.model.Player;
import com.pickleball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAllByOrderByRatingDesc();
        return players.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PlayerDTO> getPlayerById(Long id) {
        return playerRepository.findById(id)
                .map(this::convertToDTO);
    }

    public PlayerDTO createPlayer(Player player) {
        if (player.getCreatedAt() == null) {
            player.setCreatedAt(java.time.LocalDateTime.now().toString());
        }
        Player saved = playerRepository.save(player);
        return convertToDTO(saved);
    }

    public PlayerDTO updatePlayer(Long id, Player playerDetails) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            Player p = player.get();
            if (playerDetails.getName() != null) p.setName(playerDetails.getName());
            if (playerDetails.getEmail() != null) p.setEmail(playerDetails.getEmail());
            if (playerDetails.getSkillLevel() != null) p.setSkillLevel(playerDetails.getSkillLevel());
            if (playerDetails.getWins() != null) p.setWins(playerDetails.getWins());
            if (playerDetails.getLosses() != null) p.setLosses(playerDetails.getLosses());
            if (playerDetails.getRating() != null) p.setRating(playerDetails.getRating());
            if (playerDetails.getLocation() != null) p.setLocation(playerDetails.getLocation());
            Player updated = playerRepository.save(p);
            return convertToDTO(updated);
        }
        return null;
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    private PlayerDTO convertToDTO(Player player) {
        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getEmail(),
                player.getSkillLevel(),
                player.getWins(),
                player.getLosses(),
                player.getRating(),
                player.getRank(),
                player.getLocation(),
                player.getPhoneNumber(),
                player.getCreatedAt()
        );
    }
}
