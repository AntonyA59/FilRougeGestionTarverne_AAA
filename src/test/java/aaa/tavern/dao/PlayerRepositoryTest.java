package aaa.tavern.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aaa.tavern.entity.Player;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    @Sql("givenPlayer_findByIdPlayer_thenReturnPlayer.sql")
    public void givenPlayer_findByIdPlayer_thenReturnPlayer() {
        List<Player> players = playerRepository.findByIdPlayer(7);
        assertEquals(players.size(), 1);
    }
}
