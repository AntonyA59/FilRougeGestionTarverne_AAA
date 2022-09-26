package aaa.tavern.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;

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
        Optional<Manager> managerOpt= managerRepository.findById(manager.getManagerId());
        manager = managerOpt.get();
        managerRepository.delete(manager);

    }

    public List<Manager> listExistingManager(Player player) {
        List<Manager> managers = managerRepository.findByPlayer(player);
        if(managers.isEmpty()){
            throw new EntityNotFoundException();
        }
        return managers;
    }

    public Manager selectManager(Manager manager){
        Optional<Manager> managerOpt = managerRepository.findById(manager.getManagerId());
        manager = managerOpt.get();
        return manager;
    }

    public ManagerDto loadManager(Manager manager){
        return new ManagerDto(manager.getName(), manager.getReputation(), manager.getChest(), manager.getLevel(), manager.getIngredientQuantity());

    }

}
