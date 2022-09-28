package aaa.tavern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import aaa.tavern.Entity.Player;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.PlayerDto;

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
