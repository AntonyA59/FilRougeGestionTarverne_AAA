package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.RecipeCustomerKey;


@Repository
public interface RecipeCustomerRepository extends CrudRepository<RecipeCustomer, RecipeCustomerKey> {
    
    
}
