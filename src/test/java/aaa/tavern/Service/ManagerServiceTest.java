package aaa.tavern.Service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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
import aaa.tavern.Service.ManagerService;
import aaa.tavern.dto.ManagerDto;


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

    @Test
    public void listExistingManagerDto(){
        Player player = new Player();
        Manager manager1 = new Manager("Test1", 10, 20, 20, 20, player);
        Manager manager2 = new Manager("Test2", 10, 20, 20, 20, player);
        Manager manager3 = new Manager("Test3", 10, 20, 20, 20, player);
        List<Manager> managers = new ArrayList<Manager>();
        managers.add(manager1);
        managers.add(manager2);
        managers.add(manager3);
        Mockito.when(managerRepository.findByPlayer(player)).thenReturn(managers);
        List<ManagerDto> managerDtos = managerService.listExistingManagerDto(player) ;
        
        assertEquals(managerDtos.size(), 3);
    }

    
}
