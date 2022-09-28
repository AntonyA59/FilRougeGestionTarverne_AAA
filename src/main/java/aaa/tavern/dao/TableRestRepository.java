package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.Entity.TableRest;


@Repository
public interface TableRestRepository extends CrudRepository<TableRest, Integer> {
    
}
