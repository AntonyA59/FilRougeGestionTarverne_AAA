package aaa.tavern.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.Manager;
import aaa.tavern.Entity.Player;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    List<Manager>findByPlayer(Player player);
    
}
