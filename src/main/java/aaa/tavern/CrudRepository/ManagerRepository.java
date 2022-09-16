package aaa.tavern.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.Manager;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    
    
}
