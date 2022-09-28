package aaa.tavern.Service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.PlayerRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.Entity.Player;

@Service
public class PlayerService {
    @Autowired 
    private PlayerRepository userRepository;

    //retourne l'id du player créer
    public int createPlayer(PlayerDto userDto) {
        try{
            Player newPlayer = new Player(userDto.getEmail(), userDto.getNickname(), userDto.getPassword()) ;
            userRepository.save(newPlayer) ;
            return newPlayer.getIdPlayer() ;
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
