package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.ManagerCustomer;

@Repository
public interface ManagerCustomerRepository extends CrudRepository<ManagerCustomer, Integer> {
    
}
