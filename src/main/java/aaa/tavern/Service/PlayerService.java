package aaa.tavern.Service;

import java.util.List;

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
    private PlayerRepository playerRepository;

    public int createPlayer(PlayerDto playerDto) {
        try{
            Player newPlayer = new Player(playerDto.getEmail(), playerDto.getNickname(), playerDto.getPassword()) ;
            playerRepository.save(newPlayer) ;
            return newPlayer.getIdPlayer() ;
        }catch(DataAccessException e){
            throw e ;
        }
    }

    public void deletePlayer(int idPlayer){
        try{
            playerRepository.deleteById(idPlayer);
        }catch(DataAccessException e){
            throw e ;
        }
    }

    public int Connexion(PlayerDto playerDto) throws EntityNotFoundException {
        try{
            List<Player> player = playerRepository.findByEmail(playerDto.getEmail()) ;

            if(!player.isEmpty())
			    throw new EntityNotFoundException();

            if(player.get(0).getPassword().equals(playerDto.getPassword())){
                
            }else{
                // password invalid
            }

            return 0 ;
        }catch(DataAccessException e){
            throw e ;
        }
    }
}
