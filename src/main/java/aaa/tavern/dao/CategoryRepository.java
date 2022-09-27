package aaa.tavern.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.Category;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
}
