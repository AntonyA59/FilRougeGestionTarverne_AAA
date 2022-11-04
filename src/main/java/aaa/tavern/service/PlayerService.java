package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
    ApplicationEventPublisher eventPublisher ;

    public void createPlayer(PlayerDto playerDto/*, HttpServletRequest request*/) {
        Role playerRole = roleRepository.findByName("USER").get() ;
        List<Role> roles = new ArrayList<Role>();
        roles.add(playerRole);
        Player newPlayer = new Player(
            playerDto.getEmail(), 
            playerDto.getNickname(), 
            passwordEncoder.encode(playerDto.getPassword()),
            true,
            roles
            ) ;
        //eventPublisher.publishEvent(new OnRegistrationCompleteEvent(newPlayer, request));
        playerRepository.save(newPlayer);
    }

    // TODO méthodes de modification du Player sensible aux hacks
    public boolean changeNickname(int idPlayer, String nickname) {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        if (optPlayer.isEmpty()) {
            return false;
        } else {
            optPlayer.get().setNickname(nickname);
            playerRepository.save(optPlayer.get());
            return true;
        }
    }

    public boolean changeEmail(int idPlayer, String email) {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        if (optPlayer.isEmpty()) {
            return false;
        } else {
            optPlayer.get().setNickname(email);
            playerRepository.save(optPlayer.get());
            return true;
        }
    }

    public boolean changePassword(int idPlayer, String password){
        Optional<Player> optPlayer =  playerRepository.findById(idPlayer) ;

        if(optPlayer.isEmpty()){
            return false ;
        }else{
            optPlayer.get().setPassword(passwordEncoder.encode(password));
            playerRepository.save(optPlayer.get());
            return true;
        }
    }

    public boolean deletePlayer(int idPlayer) {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        if (optPlayer.isEmpty()) {
            return false;
        } else {
            playerRepository.deleteById(idPlayer);
            return true;
        }
    }

    public boolean enabledPlayer(int idPlayer) {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        if (optPlayer.isEmpty()) {
            return false;
        } else {
            optPlayer.get().setEnabled(true);
            playerRepository.save(optPlayer.get());
            return true;
        }
    }

    public boolean disabledPlayer(int idPlayer) {
        Optional<Player> optPlayer = playerRepository.findById(idPlayer);
        if (optPlayer.isEmpty()) {
            return false;
        } else {
            optPlayer.get().setEnabled(false);
            playerRepository.save(optPlayer.get());
            return true;
        }
    }
    /*
     * ////// Inutile avec Baeldung ///////
     * 
     * public int Connexion(PlayerDto userDto) throws EntityNotFoundException {
     * try {
     * Optional<Player> user = playerRepository.findByEmail(userDto.getEmail());
     * 
     * if (!user.isEmpty())
     * throw new EntityNotFoundException();
     * 
     * if (user.get().getPassword().equals(userDto.getPassword())) {
     * 
     * } else {
     * // password invalid
     * }
     * 
     * return 0;
     * } catch (DataAccessException e) {
     * throw e;
     * }
     * }
     */
}
