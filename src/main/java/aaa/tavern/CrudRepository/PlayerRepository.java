package aaa.tavern.CrudRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Player;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
    List<Player> findByPlayerId(Integer playerId);
    List<Player> findByEmail(String email) ;
    List<Player> findByNickname(String nickname) ;
}