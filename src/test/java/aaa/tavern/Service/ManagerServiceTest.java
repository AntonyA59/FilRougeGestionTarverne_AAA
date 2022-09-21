package aaa.tavern.Service;



import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.DAO.PlayerRepository;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;



@SpringBootTest
public class ManagerServiceTest {


    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerService managerService;


    @Test
    public void createManager() {
        Manager manager = Mockito.mock(Manager.class);
        Player player = Mockito.mock(Player.class);
        manager = managerService.createManager("test", player);
        Mockito.verify(managerRepository).save(manager);
    }

    @Test
    public void deleteManager() {
        Manager manager = Mockito.mock(Manager.class);
        Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));
        managerService.deleteManager(manager);
        Mockito.verify(managerRepository).delete(manager);
    }
}