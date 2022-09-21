package aaa.tavern.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import aaa.tavern.CrudRepository.PlayerRepository;
import aaa.tavern.Entity.Player;

@Service
public class InscriptionPlayerService {
    @Autowired 
    private PlayerRepository playerRepository;

    public boolean createPlayer(PlayerDto playerDto) {
        try{
            Player newPlayer = new Player(playerDto.getEmail(), playerDto.getNickname(), playerDto.getPassword()) ;
            playerRepository.save(newPlayer) ;
            return true ;
        }catch(DataAccessException r){
            throw r ;
        }
    }
}
