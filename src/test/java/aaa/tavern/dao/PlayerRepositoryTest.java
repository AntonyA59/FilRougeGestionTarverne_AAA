package aaa.tavern.dao;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertEquals;

import aaa.tavern.entity.Player;


@DataJpaTest
public class PlayerRepositoryTest {
	@Autowired
	private PlayerRepository playerRepository;

	@Test
	@Sql("givenPlayer_findByEmail_thenReturnPlayer.sql")
	public void givenManagerAndPlayer_findByIdPlayer_thenReturnManager() {
		Optional<Player> playerOpt = playerRepository.findByEmail("Test2@test.com");
		Player player = playerOpt.get();
		assertEquals(player.getEmail(), "Test2@test.com");
	}

    @Test
    @Sql("givenPlayer_findByEmail_thenReturnPlayer.sql")
	public void givenManagerAndPlayer_findByNickname_thenReturnManager() {
		List<Player> playerOpt = playerRepository.findByNickname("Test3");
		Player player = playerOpt.get(0) ;
		assertEquals(player.getNickname(), "Test3");
	}
}
