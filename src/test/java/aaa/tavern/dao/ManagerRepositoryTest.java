package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlayerRepository;


@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ManagerRepositoryTest {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @Sql("givenManagerAndPlayer_findByIdPlayer_thenReturnManager.sql")
    public void givenManagerAndPlayer_findByIdPlayer_thenReturnManager() {
        List<Player> players = playerRepository.findByIdPlayer(2);
        Player player = players.get(0);
        List<Manager> managers = managerRepository.findByPlayer(player);
        assertEquals(managers.size(), 3);
    }
}
