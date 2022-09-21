package aaa.tavern.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.DAO.PlayerRepository;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;



@SpringBootTest
public class ManagerServiceTest {


    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private ManagerService managerService;


    @Test
    public void createManager() {
        Manager manager = new Manager();
        Player player = Mockito.mock(Player.class);
        Mockito.when(playerRepository.findById(0)).thenReturn(Optional.of(player));
        manager = managerService.createManager("test", player);
        assertEquals(manager.getChest(), 100);
    }
}
