package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.ManagerCustomer;
import aaa.tavern.Entity.Manager;

@Repository
public interface ManagerCustomerRepository extends CrudRepository<ManagerCustomer, Integer> {
    List<ManagerCustomer> findByManager(Manager manager);
}
