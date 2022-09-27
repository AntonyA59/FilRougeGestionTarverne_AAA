package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.RecipeCustomer;


@Repository
public interface RecipeCustomerRepository extends CrudRepository<RecipeCustomer, Integer> {
    
    
}
