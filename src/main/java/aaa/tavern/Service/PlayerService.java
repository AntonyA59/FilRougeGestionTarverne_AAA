package aaa.tavern.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.PlayerDto;
import aaa.tavern.entity.Player;

@Service
public class PlayerService {
    @Autowired 
    private PlayerRepository playerRepository;

    //retourne l'id du player créer
    public int createPlayer(PlayerDto playerDto) {
        try{
            Player newPlayer = new Player(playerDto.getEmail(), playerDto.getNickname(), playerDto.getPassword()) ;
            playerRepository.save(newPlayer) ;
            return newPlayer.getIdPlayer() ;
        }catch(DataAccessException e){
            throw e ;
        }
    }

    public boolean deletePlayer(int idPlayer){
        try{
            playerRepository.deleteById(idPlayer);
            return true ;
        }catch(DataAccessException e){
            return false ;
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
