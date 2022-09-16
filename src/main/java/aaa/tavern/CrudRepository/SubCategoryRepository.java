package aaa.tavern.CrudRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import aaa.tavern.Entity.SubCategory;


@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {
    
}
