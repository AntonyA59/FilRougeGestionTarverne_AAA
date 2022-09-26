package aaa.tavern.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
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
        Optional<Manager> managerOpt= managerRepository.findById(manager.getIdManager());
        manager = managerOpt.get();
        managerRepository.delete(manager);
    }

    public void listExistingManager() {
        
    }
}
