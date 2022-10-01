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

@SpringBootTest
public class ManagerServiceTest {

	@MockBean
	private PlayerRepository playerRepository;

	@MockBean
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerService managerService;

	@Test
	public void whenCreateManager_thenSaveOnDatabase() {
		Manager manager = new Manager();

		Player player = Mockito.mock(Player.class);

		Mockito.when(playerRepository.findById(0)).thenReturn(Optional.of(player));
		manager.setPlayer(player);
		ManagerDto managerDto = new ManagerDto(manager);
		managerService.createManager(managerDto, 0);

		Mockito.verify(managerRepository).save(manager);

	}

	@Test
	public void deleteManager() {
		Manager manager = Mockito.mock(Manager.class);
		Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));
		managerService.deleteManager(0);

		Mockito.verify(managerRepository).deleteById(0);
	}

	@Test
	public void listExistingManagerDto() {
		Player player = Mockito.mock(Player.class);
		Mockito.when(playerRepository.findById(1)).thenReturn(Optional.of(player));
		Manager manager1 = new Manager("Test1", 10, 20, 20, 20, player);
		Manager manager2 = new Manager("Test2", 10, 20, 20, 20, player);
		Manager manager3 = new Manager("Test3", 10, 20, 20, 20, player);
		List<Manager> managers = new ArrayList<Manager>();
		managers.add(manager1);
		managers.add(manager2);
		managers.add(manager3);
		Mockito.when(managerRepository.findByPlayer(player)).thenReturn(managers);

		List<ManagerDto> listManagerDtos = new ArrayList<ManagerDto>();
		ManagerDto managerDto1 = new ManagerDto(manager1);
		ManagerDto managerDto2 = new ManagerDto(manager2);
		ManagerDto managerDto3 = new ManagerDto(manager3);
		listManagerDtos.add(managerDto1);
		listManagerDtos.add(managerDto2);
		listManagerDtos.add(managerDto3);

		List<ManagerDto> listManagerDtos2 = managerService.listExistingManagerDto(1);

		assertEquals(listManagerDtos, listManagerDtos2);
	}

}
