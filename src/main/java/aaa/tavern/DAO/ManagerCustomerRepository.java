package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.ManagerCustomer;

@Repository
public interface ManagerCustomerRepository extends CrudRepository<ManagerCustomer, Integer> {
    
}
