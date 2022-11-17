package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dao.RoleRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.Role;
//import aaa.tavern.service.utils.OnRegistrationCompleteEvent;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public void createPlayer(PlayerDto playerDto) {
        Role playerRole = roleRepository.findByName("USER").get();
        List<Role> roles = new ArrayList<Role>();
        roles.add(playerRole);
        Player newPlayer = new Player(
                playerDto.getEmail(),
                playerDto.getNickname(),
                passwordEncoder.encode(playerDto.getPassword()),
                true,
                roles);
        playerRepository.save(newPlayer);
    }

    public boolean changeNickname(int idPlayer, String nickname) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {

            if (optPlayer.isEmpty()) {
                return false;
            } else {
                optPlayer.get().setNickname(nickname);
                playerRepository.save(optPlayer.get());
                return true;
            }
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

    public boolean changeEmail(int idPlayer, String email) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {

            if (optPlayer.isEmpty()) {
                return false;
            } else {
                optPlayer.get().setNickname(email);
                playerRepository.save(optPlayer.get());
                return true;
            }
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

    public boolean changePassword(int idPlayer, String password) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {

            if (optPlayer.isEmpty()) {
                return false;
            } else {
                optPlayer.get().setPassword(passwordEncoder.encode(password));
                playerRepository.save(optPlayer.get());
                return true;
            }
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

    public boolean deletePlayer(int idPlayer) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {

            if (optPlayer.isEmpty()) {
                return false;
            } else {
                playerRepository.deleteById(idPlayer);
                return true;
            }
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

    public boolean enabledPlayer(int idPlayer) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {

            if (optPlayer.isEmpty()) {
                return false;
            } else {
                optPlayer.get().setEnabled(true);
                playerRepository.save(optPlayer.get());
                return true;
            }
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

    public boolean disabledPlayer(int idPlayer) throws Exception {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(optPlayer.get().getEmail())) {
            if (optPlayer.isEmpty()) {
                return false;
            } else {
                optPlayer.get().setEnabled(false);
                playerRepository.save(optPlayer.get());
                return true;
            }

        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }

    }

    public Player loadPlayerByEmail(String email) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(email)) {

            return playerRepository.findByEmail(email).get();
        } else {
            throw new Exception("Ce joueur ne correspond pas a votre compte");
        }
    }

}
