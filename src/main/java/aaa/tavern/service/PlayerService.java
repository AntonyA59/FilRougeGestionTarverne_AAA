package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dao.RoleRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.Role;


@Service
public class PlayerService {
    @Autowired 
    private PlayerRepository userRepository;

    @Autowired 
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /** Create a new player
     * 
     * @param playerDto
     * @return return id of Player
     */
    //retourne l'id du player créer
    public void createPlayer(PlayerDto playerDto) throws DataAccessException {
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
        userRepository.save(newPlayer) ;
    }

    /**
     * 
     * @param idPlayer
     * @return return false 
     */
    public boolean deletePlayer(int idPlayer) throws DataAccessException {
        userRepository.deleteById(idPlayer);
        return true ;
    }
    /*
    ////// Inutile avec Baeldung ///////

    public int Connexion(PlayerDto userDto) throws EntityNotFoundException {
        try{
            Optional<Player> user = userRepository.findByEmail(userDto.getEmail()) ;

            if(!user.isEmpty())
			    throw new EntityNotFoundException();

            if(user.get().getPassword().equals(userDto.getPassword())){
                
            }else{
                // password invalid
            }

            return 0 ;
        }catch(DataAccessException e){
            throw e ;
        }
    }
    */
}
