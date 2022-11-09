package aaa.tavern.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.PlayerDto;
import aaa.tavern.dto.StatusDto;
import aaa.tavern.entity.Player;
import aaa.tavern.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/register")
    public StatusDto createPlayer(@Valid @RequestBody PlayerDto playerDto) {
        try {
            playerService.createPlayer(playerDto);
            return new StatusDto(1, "Le player est bien créé");

        } catch (DataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Utilisateur déjà existant");
        }
    }

    /*
     * Permet de retourner l'utilisateur connecté
     */
    @GetMapping("/game/profile")
    public Player profile(Principal principal) {
        return playerService.loadPlayerByEmail(principal.getName());
    }

}