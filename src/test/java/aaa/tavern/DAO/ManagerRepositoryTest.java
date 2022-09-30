package aaa.tavern.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;


@DataJpaTest
public class ManagerRepositoryTest {
    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @Sql("givenManagerAndPlayer_findByIdPlayer_thenReturnManager.sql")
    public void givenManagerAndPlayer_findByIdPlayer_thenReturnManager() {
        Optional<Player> optPlayer = playerRepository.findById(2);
        Player player= optPlayer.get();
        List<Manager> managers = managerRepository.findByPlayer(player);
        assertEquals(managers.size(), 3);
    }
}
