package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerService {
    
    @Autowired
    private ManagerRepository managerRepository;

   


    public Manager createManager(String name, Player player ) {
        Manager manager = new Manager(name, 0, 100, 1, 0, player);
        managerRepository.save(manager);
        return manager;
    }

    
    public void deleteManager(Manager manager) {
        Optional<Manager> managerOpt= managerRepository.findById(manager.getIdManager());
        manager = managerOpt.get();
        managerRepository.delete(manager);
    }

    /**
     * Convert a Manager class instance into a ManagerDto class instance
     * @param manager
     * @return ManagerDto
     */
    public ManagerDto loadManagerDto(Manager manager){
        return new ManagerDto(manager);
    }

    /**
     * Lists player's managers in the database and returns them in List of managerDto
     * @param player
     * @return List<ManagerDto>
     */
    public List<ManagerDto> listExistingManagerDto(Player player) {
        List<Manager> managers = managerRepository.findByPlayer(player);
        if(managers.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<ManagerDto> managersDto = new ArrayList<ManagerDto>();
        for (Manager manager : managers) {
            ManagerDto managerDto = loadManagerDto(manager);
            managersDto.add(managerDto);
        }

        return managersDto;
    }

    @Transactional(rollbackOn = {EntityNotFoundException.class, ForbiddenException.class})
    public Manager selectManager(int idManager){
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);

        return manager;
    }

}