package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.entity.ManagerCustomerKey;

@Repository
public interface ManagerCustomerRepository extends CrudRepository<ManagerCustomer, ManagerCustomerKey> {
    
}
