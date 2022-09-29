package aaa.tavern.service;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.Recipe;
import aaa.tavern.service.ManagerService;
import aaa.tavern.utils.ServiceUtil;


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
    	ManagerDto managerDto = Mockito.mock(ManagerDto.class);
        
        Mockito.when(managerService.createManager(managerDto, 0));
        
    }

    @Test
    public void deleteManager() {
        Manager manager = Mockito.mock(Manager.class);
        Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));
        managerService.deleteManager(0);
        
        Mockito.verify(managerRepository).deleteById(0);
    }

    @Test
    public void listExistingManagerDto(){
        Player player = new Player();
        playerRepository.save(player);
        Manager manager1 = new Manager("Test1", 10, 20, 20, 20, player);
        Manager manager2 = new Manager("Test2", 10, 20, 20, 20, player);
        Manager manager3 = new Manager("Test3", 10, 20, 20, 20, player);
        List<Manager> managers = new ArrayList<Manager>();
        managers.add(manager1);
        managers.add(manager2);
        managers.add(manager3);
        Optional<Player> optPlayer= Optional.of(player);
        Mockito.when(playerRepository.findById(1)).thenReturn(optPlayer);
        
        
        List<ManagerDto> managerDtos = managerService.listExistingManagerDto(1) ;

        
  
        
        
        
        assertEquals(managerDtos.size(), 3);
    }

    
}
