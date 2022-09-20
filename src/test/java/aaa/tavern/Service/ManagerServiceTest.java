package aaa.tavern.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import aaa.tavern.DAO.PlayerRepository;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;


@SpringBootTest
public class ManagerServiceTest {


    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ManagerService managerService;

    @Test
    public void createManager() {
        Manager manager = new Manager();
        Player player = new Player("Test@Test.fr", "Test", "Azerty");
        playerRepository.save(player);
        manager = managerService.createManager("test", player);
        assertEquals(manager.getName(), "test");
    }
}
