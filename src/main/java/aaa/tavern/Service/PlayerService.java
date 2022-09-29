package aaa.tavern.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.PlayerRepository;
import aaa.tavern.DAO.RoleRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.Entity.Player;
import aaa.tavern.Entity.Role;

@Service
public class PlayerService {
    @Autowired 
    private PlayerRepository userRepository;

    @Autowired 
    private RoleRepository roleRepository ;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //retourne l'id du player créer
    public Player createPlayer(PlayerDto playerDto) {
        try{
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
            return userRepository.save(newPlayer) ;
        }catch(DataAccessException e){
            throw e ;
        }
    }

    public boolean deletePlayer(int idPlayer){
        try{
            userRepository.deleteById(idPlayer);
            return true ;
        }catch(DataAccessException e){
            return false ;
        }
    }

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
}
