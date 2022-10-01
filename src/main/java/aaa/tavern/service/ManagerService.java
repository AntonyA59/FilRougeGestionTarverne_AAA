package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private PlayerRepository playerRepository;

	/**
	 * Create a manager from a Dto and save it in the database
	 * 
	 * @param managerDto
	 * @param idPlayer
	 * @throws EntityNotFoundException
	 */
	public void createManager(ManagerDto managerDto, int idPlayer) throws EntityNotFoundException {
		Player player = ServiceUtil.getEntity(playerRepository, idPlayer);
		Manager manager = new Manager(managerDto.getName(), 0, 100, 1, 0, player);
		managerRepository.save(manager);

	}

	/**
	 * Remove a manager from the database
	 * 
	 * @param managerId
	 */
	public void deleteManager(int managerId) {

		managerRepository.deleteById(managerId);
	}

	/**
	 * Convert a Manager class instance into a ManagerDto class instance
	 * 
	 * @param manager
	 * @return ManagerDto
	 * @throws EntityNotFoundException
	 */
	public ManagerDto loadManagerDto(int managerId) throws EntityNotFoundException {
		Manager manager = ServiceUtil.getEntity(managerRepository, managerId);
		return new ManagerDto(manager);
	}

	/**
	 * Lists player's managers in the database and returns them in List of
	 * managerDto
	 * 
	 * @param playerId
	 * @return List<ManagerDto>
	 */
	public List<ManagerDto> listExistingManagerDto(int playerId) {
		Player player = ServiceUtil.getEntity(playerRepository, playerId);
		List<Manager> ListManagers = managerRepository.findByPlayer(player);
		if (ListManagers.isEmpty()) {
			throw new EntityNotFoundException();
		}
		List<ManagerDto> ListManagersDto = new ArrayList<ManagerDto>();
		for (Manager manager : ListManagers) {
			ManagerDto managerDto = new ManagerDto(manager);
			ListManagersDto.add(managerDto);
		}

		return ListManagersDto;
	}

	/**
	 * Selects an existing manager in the database
	 * 
	 * @param idManager
	 * @return Manager
	 * @throws EntityNotFoundException
	 */
	@Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
	public Manager selectManager(int idManager) throws EntityNotFoundException {
		Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
		return manager;
	}

}