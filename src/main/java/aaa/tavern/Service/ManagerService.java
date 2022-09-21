package aaa.tavern.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.DAO.ManagerRepository;
import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;

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
        Optional<Manager> managerOpt= managerRepository.findById(manager.getManagerID());
        manager = managerOpt.get();
        managerRepository.delete(manager);

    }
}
