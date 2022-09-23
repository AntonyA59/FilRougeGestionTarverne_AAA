package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Manager;


@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    
    
}
